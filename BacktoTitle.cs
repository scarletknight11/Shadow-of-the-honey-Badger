using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class BacktoTitle : MonoBehaviour {

	int timer = 0;

	void Start () {
		
	}

	void Update () {
		if (Input.GetKey (KeyCode.Space)) {
			SceneManager.LoadScene ("Merged Scene");
		}
	}
}
