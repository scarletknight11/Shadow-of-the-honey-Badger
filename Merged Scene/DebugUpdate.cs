using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DebugUpdate : MonoBehaviour {
    Text txt;
    private BadgerBehavior badger;
    // Use this for initialization
    void Start () {
        txt = gameObject.GetComponent<Text>();
        badger = GameObject.FindObjectOfType<BadgerBehavior>();
    }

    // Update is called once per frame
    void Update () {
        Vector3 badgerPosition = badger.transform.position;
        int points = badger.points;
        txt.text = string.Format(@"Debug
x: {0}
y: {1}

Points: {2}
Energy: {3}", badgerPosition.x, badgerPosition.y, points, badger.getEnergy());
	}
}
