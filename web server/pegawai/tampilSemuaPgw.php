<?php 
	//import file koneksi database
	require_once('connection.php');

	//membuat SQL query
	$sql = "SELECT * FROM tb_pegawai;";

	//mendapatkan hasil
	$r = mysqli_query($con, $sql);

	//membuat array kosong
	$result = array();

	while ($row = mysqli_fetch_array($r)) {
		//memasukkan nama dan id kedalam array kosong yang telah dibuat
		array_push($result, array(
			"id"=>$row['id'],
			"name"=>$row['nama']
		));
	}

	//menampilkan array dalam format JSON
	echo json_encode(array('result'=>$result));

	mysqli_close($con);

 ?>