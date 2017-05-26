using UnityEngine;
using System.Collections;

public class ai : MonoBehaviour 
{

	public Vector3 leftPoint;
	public Vector3 rightPoint;
	public float Xpos;
	public float flipL;
	public float flipR;
	public float botSpeed = 0.5f;
	public bool aiSwitch = false;
	public SpriteRenderer rend;
	// Use this for initialization
	void Start () 
	{ 
		rend = gameObject.GetComponent<SpriteRenderer> ();
		leftPoint = gameObject.transform.position;
		rightPoint = new Vector3 (gameObject.transform.position.x + 7, gameObject.transform.position.y, gameObject.transform.position.z);
		aiSwitch = true;	
		flipL = gameObject.transform.position.x + 0.3f;
		flipR = gameObject.transform.position.x + 6.7f;
		 
	}

	// Update is called once per frame
	void Update () 
	{
		Xpos = gameObject.transform.position.x;

		Debug.DrawLine (leftPoint, rightPoint, Color.red, 10f);

		if (aiSwitch) 
		{
			transform.position = Vector3.Lerp (leftPoint, rightPoint, Mathf.PingPong (botSpeed * Time.time, 1f));
		}
		if (Xpos < flipL) {
			rend.flipX = true;
		} else if (Xpos > flipR) {
			rend.flipX = false;
		}
	}

	 


}
