<?php 

if ($_SERVER['REQUEST_METHOD']=='POST') {
	//mendapatkan nilai variable
	$name = $_POST['name'];
	$desg = $_POST['desg'];
	$sal = $_POST['salary'];

	//pembuatan sytanx SQL 
	$sql = "INSERT INTO tb_pegawai (nama, posisi, gaji) VALUES ('$name','$desg','$sal');";

	//import kelas file koneksi database
	require_once('connection.php');

	//eksekusi query database
	if (mysqli_query($con, $sql)) {
		echo 'Berhasil menambahkan pegawai';
	} else {
		echo 'Gagal menambahkan pegawai';
	}

	mysqli_close($con);
}
?>

