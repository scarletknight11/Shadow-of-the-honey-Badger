using UnityEngine;
using System.Collections;

public class DropBotTrigger : MonoBehaviour {


	public AI_move triggerSwitch;

	//public float jumpSpeed = 10f;


	// Use this for initialization
	void Start () 
	{
		triggerSwitch = GameObject.Find ("Snake").GetComponent<AI_move> ();

		//scanPt = GameObject.Find ("sqaure").transform;

	}

	// Update is called once per frame
	void Update () 
	{



		// && = and
		// || = or



	}

	 
	}

