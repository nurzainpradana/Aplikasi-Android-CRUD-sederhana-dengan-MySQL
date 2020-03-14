<?php 

if($_SERVER['REQUEST_METHOD']=='POST'){
	//mendapatkan nilai dari variable
	$id = $_POST['id'];
	$name = $_POST['name'];
	$desg = $_POST['desg'];
	$sal = $_POST['salary'];

	//import file ke koneksi databse
	require_once('connection.php');

	//membuat sql query
	$sql = "UPDATE tb_pegawai SET nama = '$name', posisi = '$desg', gaji = '$sal' WHERE id = '$id';";

	//meng-update database
	if (mysqli_query($con, $sql)) {
		echo 'Berhasil Update data pegawai';
	} else {
		echo 'Gagal Update data pegawai';
	}

	mysqli_close($con);

}

 ?>