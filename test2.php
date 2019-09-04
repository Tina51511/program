<?php
$serverName = "140.131.114.241"; //serverName\instanceName
$connectionInfo = array( "Database"=>"108-plantfun", "UID"=>"ntub108203", "PWD"=>"@Ntub108203", "CharacterSet" => "UTF-8");
$con = sqlsrv_connect( $serverName, $connectionInfo);
$sql = "SELECT * FROM 蕨類 where SID=1";
 $result=sqlsrv_query($con,$sql);//user資料表名稱
 while($row=sqlsrv_fetch_array($result))
    {
        echo $row['中文名']."<br>";
        echo $row['學名']."<br>";
        echo $row['原產地']."<br>";
        echo $row['生活型']."<br>";
        echo $row['樹幹及枝條的描述']."<br>";
        echo $row['葉的描述']."<br>";
        echo $row['蕨類孢子囊群描述']."<br>";
        echo $row['生育環境']."<br>";
        echo $row['用途說明']."<br>";   
       }
 sqlsrv_close($con);
?>