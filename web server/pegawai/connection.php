<?php 

/* Nama Database yang sudah dibuat bernama "db_android"
*/

//mendefinisikan konstanta
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','db_android');

//membuat koneksi dengan database
$con = mysqli_connect(HOST, USER, PASS, DB) or die ('Unable to connect');

 ?>