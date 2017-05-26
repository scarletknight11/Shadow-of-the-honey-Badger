using UnityEngine;
using UnityEngine.UI;
using System.Collections;

//Code by Sanjit Singh


public class PlayerScript : MonoBehaviour {
    
    private Rigidbody2D rigidBody;
    public static float BASE_FORCE = 10;
    public float speed;
    public Text countText;
    public Text winText;
    public float energy = 50;
    private int count;
	private Animator animator;
	public Vector2 initialVelocity;



     

	// Use this for initialization
	void Start () 
    {
     
		rigidBody = GetComponent<Rigidbody2D>();
		rigidBody.velocity = initialVelocity;

		animator = GetComponent<Animator>();

        count = 0;
        SetCountText();
        winText.text = "";
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
		bool isMoving = moveVertical == 0;

		if (isUnderground())
		{
			// only slow badger down when he's not moving himself
			float drag = isMoving ? 0.8f : 0.0f;
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
		// Give x momentum
		if (!isUnderground() || isMoving)
		{
			float horizontalForceToApply = BASE_FORCE - currentVelocity.x;
			rigidBody.AddForce(new Vector2(horizontalForceToApply, 0));
		}

		// set badger heading to the current velocity angle
		float angle = Mathf.Atan2(currentVelocity.y, currentVelocity.x) * 180 / Mathf.PI - 90;
		if (currentVelocity.magnitude == 0)
		{
			angle = -90;
		}
		transform.eulerAngles = new Vector3(transform.eulerAngles.x, transform.eulerAngles.y, angle);

		float animationSpeed = Mathf.Min(1, currentVelocity.magnitude / 3);
		animator.speed = animationSpeed;
	}

	private void LateUpdate()
	{
		if (isUnderground())
		{
			Debug.Log(rigidBody.velocity.magnitude);
			if (rigidBody.velocity.magnitude < 1)
			{
				rigidBody.velocity = new Vector2(0, 0);
			}
		}
	}

    void OnTriggerEnter2D(Collider2D other)
    {
		if (other.gameObject.CompareTag("Honey"))
        {
            other.gameObject.SetActive(false);
            count = count + 1;
            SetCountText();
        }
    }

    void SetCountText()
    {
        countText.text = "Count: " + count.ToString();
        if (count >= 12)
        {
            winText.text = "You Win!";
        }
    }
}
