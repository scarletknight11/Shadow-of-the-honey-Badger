using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

    public GameObject player;       //Public variable to store a reference to the player game object

    private Vector3 offset;         //Private variable to store the offset distance between the player and camera

    private static float FULL_VELOCITY_OFFSET_MAGNITUDE = 5f;

    // Use this for initialization
    void Start()
    {
        //Calculate and store the offset value by getting the distance between the player's position and camera's position.
        offset = transform.position - player.transform.position;
        // move the camera to the right of the user
        offset += new Vector3(2f, 0, 0);
    }

    // LateUpdate is called after Update each frame
    void LateUpdate()
    {
        Vector2 playerVelocity = player.GetComponent<Rigidbody2D>().velocity;
        float speed = playerVelocity.magnitude;
        Vector3 fullVelocityOffset = playerVelocity.normalized * FULL_VELOCITY_OFFSET_MAGNITUDE;
        float weight = speed / (speed + 3);
        // Set the position of the camera's transform to be the same as the player's, but offset by the calculated offset distance.
        transform.position = player.transform.position + offset + weight * fullVelocityOffset;

        float fieldOfView = 60 + Mathf.Clamp((speed - 20) * 1, 0, 30);
        Camera.main.fieldOfView = fieldOfView;
    }
}
