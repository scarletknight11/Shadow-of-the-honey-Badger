using System.Collections.Generic;
using UnityEngine;

public class BadgerBehavior : MonoBehaviour
{
    private Rigidbody2D rigidBody;
    private Animator animator;
    public static float BASE_FORCE = 10;
    public float speed = 20;
    public float energy = 50;
    public int points = 0;
	 


    public Vector2 initialVelocity;

    // Use this for initialization
    void Start()
    {
        rigidBody = GetComponent<Rigidbody2D>();
        rigidBody.velocity = initialVelocity;
	 
        animator = GetComponent<Animator>();
    }

    public float getEnergy()
    {
        return energy;
    }

    public bool isUnderground()
    {
        return transform.position.y < 100;
    }

    // Update is called once per frame
    void Update()
    {
        // jump if user pressed button
        float moveVertical = Input.GetAxis("Vertical");
        bool isUserInputting = moveVertical != 0;

		if (points >= 3000) 
		{
			// You Win Message


		}
		 
		if (energy <= 0) 
		{
			// You Lose Message


		}

        if (isUnderground())
        {
            // only slow badger down when he's not moving himself
            float drag = isUserInputting ? 0.0f : 0.8f;
            rigidBody.drag = drag;
            rigidBody.gravityScale = 0;
        } else
        {
            rigidBody.drag = 0;
            rigidBody.gravityScale = 1;
        }

        if (energy > 0 && isUnderground()) {
            float energyUsed = Mathf.Abs(moveVertical) * 0.1f;
            // ensure energy never goes below 0
            energy = Mathf.Max(0, energy - energyUsed);
            Vector2 upwardForce = new Vector2(0, moveVertical * speed);
            rigidBody.AddForce(upwardForce);
        }

        Vector2 currentVelocity = rigidBody.velocity;
        // Apply x momentum when above ground, or underground but 
        if (!isUnderground() || isUserInputting)
        {
            float horizontalForceToApply = BASE_FORCE - currentVelocity.x;
            rigidBody.AddForce(new Vector2(horizontalForceToApply, 0));
        }

        // set badger heading to the current velocity angle
        float angle = Mathf.Atan2(currentVelocity.y, currentVelocity.x) * 180 / Mathf.PI - 90;
        if (currentVelocity.magnitude != 0)
        {
            transform.eulerAngles = new Vector3(transform.eulerAngles.x, transform.eulerAngles.y, angle);
        }

        float animationSpeed = currentVelocity.magnitude / 25;
        animator.speed = animationSpeed;
    }

    private void LateUpdate()
    {
        if (isUnderground())
        {
            Debug.Log(rigidBody.velocity.magnitude);
            if (rigidBody.velocity.magnitude < 0.1)
            {
                rigidBody.velocity = new Vector2(0, 0);
            }
        }
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        if (collision.CompareTag("Honey"))
        {
            Destroy(collision.gameObject);
            points += 100;
            energy += 10;
        }

		if (collision.CompareTag("Snake"))
		{
			Destroy(collision.gameObject);
			points -= 30;
			energy -= 20;
		}

		if (collision.CompareTag("Bird"))
		{
			Destroy(collision.gameObject);
			points += 200;
			 
		}

		if (collision.CompareTag("Boulder"))
		{
			Destroy(collision.gameObject);

			energy -= 30;
		}


    }
}