<?php
	$con = mysqli_connect(
/*host*/			'us-cdbr-iron-east-05.cleardb.net',
/*user*/			'b0f5ed42e0dfff',
/*password*/		'1cb9c508',
/*basedatos*/		'heroku_721155528fbdc0e'
//estos datos se sacan de la cadena de conexion
	);

	if(!$con){
		echo "no se conecto";
		return;
	}
	//Obteniendo variables desde formulario
	$descripcion =$_POST['descripcion'];
	$monto 		 =$_POST['monto'];
	$fecha		 =$_POST['fecha'];
	$pago 		 =$_POST['pago'];
	

	$SQL = "INSERT INTO tablaSanchez(descripcion, monto, fecha, pago)values('$descripcion','$monto','$fecha', '$pago')";

	$respuesta = mysqli_query($con, $SQL);
	if($respuesta){
		echo "Si se capturo";
	}else{
		echo "No se capturo";
	}
?>