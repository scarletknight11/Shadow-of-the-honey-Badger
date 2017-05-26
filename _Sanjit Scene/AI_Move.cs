using UnityEngine;
using System.Collections;

public class AI_move : MonoBehaviour 
{

	public Vector3 leftPoint;
	public Vector3 rightPoint;
	public float botSpeed = 0.5f;
	public bool aiSwitch = false;
	 


	// Use this for initialization
	void Start () 
	{
		leftPoint = GameObject.Find ("Left").transform.position;
		rightPoint = GameObject.Find ("Right").transform.position;	
		aiSwitch = true;
	 

	}


	// Update is called once per frame
	void Update () 
	{

		Debug.DrawLine (leftPoint, rightPoint, Color.green, 10f);

		if (aiSwitch) {
			transform.position = Vector3.Lerp (leftPoint, rightPoint, Mathf.PingPong (botSpeed * Time.time, 1f));
		}

	}
	 



	}

	


