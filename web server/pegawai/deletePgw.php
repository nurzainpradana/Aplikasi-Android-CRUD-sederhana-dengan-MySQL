<?php 
	//mendapatkan nilai ID
	$id = $_GET['id'];

	//import file koneksi databse
	require_once('connection.php');

	//membuat SQL Query
	$sql = "DELETE FROM tb_pegawai WHERE id = $id;";

	//mengapus nilai pada database
	if (mysqli_query($con, $sql)) {
		echo 'Berhasil menghapus pegawai';
	} else {
		echo 'Gagal menghapus pegawai';
	}

	mysqli_close($con);
?>