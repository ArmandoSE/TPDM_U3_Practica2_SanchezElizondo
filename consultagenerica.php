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

	$SQL = "SELECT * FROM tablasanchez";
	$respuesta = mysqli_query($con,$SQL);

	$numRenglones = mysqli_num_rows($respuesta);
	if($numRenglones>0){
		while ($renglon = $respuesta->fetch_object()) {
			$data[] = $renglon;
		}
		echo (json_encode($data));
	}else{
		echo "no hay datos";
	}
















	/*$renglon = mysqli_fetch_row($respuesta);
	if ($renglon) {
		do{
			echo "id: $renglon[0],Desc: $renglon[1],Fecha: $renglon [2],Lugar: $renglon[3]";	
			$renglon = mysqli_fetch_row($respuesta)
		}while ($renglon);*/
?>