<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

$servername = "localhost";
$username = "id10840443_user";
$password = "12345678Aa@123";
$dbname = "id10840443_springbootdb";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
}

if (isset($_GET["pid"])) {
    $pid = $_GET['pid'];


    $sql = "SELECT *FROM products WHERE pid = $pid";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
      // output data of each row
      while($row = $result->fetch_assoc()) {
        //echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";

        $product = array();
            $product["pid"] = $row["pid"];
            $product["name"] = $row["name"];
            $product["price"] = $row["price"];
            $product["description"] = $row["description"];
            $product["created_at"] = $row["created_at"];
            $product["updated_at"] = $row["updated_at"];
            // success
            //$response["success"] = 1;

            // user node
            $response["products"] = array();

            array_push($response["products"], $product);

            // echoing JSON response
            echo json_encode($response);

      }
    } else {
        // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
      //echo "0 results";
    }
    $conn->close();

} else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
        echo json_encode($response);
} 
?>
