<?php 

	//mendapatkan nilai dari variable ID pegawai
	//yang ingin ditampilkan
	$id = $_GET['id'];

	//Importing database
	require_once('connection.php');

	//membuat SQL query dengan pegawai yang ditentukan secara spesifik sesuai dengan ID
	$sql = "SELECT * FROM tb_pegawai WHERE id=$id;";

	//mendapatkan hasil
	$r = mysqli_query($con, $sql);

	//Memasukkan hasil ke dalam array
	$result = array();
	$row = mysqli_fetch_array($r);
	array_push($result, array(
		"id" => $row['id'],
		"name" => $row['nama'],
		"desg" => $row['posisi'],
		"salary" => $row['gaji']
	));

	//Menampilkan dalam format JSON
	echo json_encode(array('result' => $result));
	mysqli_close($con);
 ?>