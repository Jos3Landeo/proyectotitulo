<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/estiloLogin.css">
        <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
        <link rel = "icon" type="image/png" href = "Img/logo.png">
        <script type="text/javascript" src="Recursos/funciones.js"></script>

        <!-- Font Awesone : Para los iconos-->
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://kit.fontawesome.com/28f5db2784.js" crossorigin="anonymous"></script>

        <!-- bootstrap : Js,css y mas -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script><!-- comment -->

    </head>
    <body class="text-center">
        
        <div class="completo">
            <form action="Login" class="login" name="inicio_sesion" method="POST" onsubmit="return validarForma(this)">
                <div style="color: #2196F3">
                    <i class="fab fa-product-hunt fa-10x"></i>
                </div>
                <p class="titulo">Login de usuarios</p>
                
                <div class="py-2">
                    <input class="default" type="text" name="usuario" placeholder="Escriba usuario" onfocus="myUser(this)"/>
                    <i class="fa fa-user" name="user"></i>
                </div>
                <div class="py-2">
                    <input class="default" type="password" name="password" placeholder="Escriba contraseña" onfocus="myPass(this)"/>
                    <i class="fa fa-key" name="pass"></i>
                </div>

                <div style="color: red">${noEncontrado}</div>
                <input class="boton" type="submit" value="Ingresar"/>
            </form>
                <div style="background: lightblue;font-weight: bold; position: fixed; left: 500px;box-shadow:20px 20px 50px 10px;padding: 10px">
                    <p>Usuarios</p>
                    <p>funcionario_alto - 123 </p>
                    <p>funcionario_bajo - 123</p>
                    <p>diseñador - 123</p>
                </div>
            <p class="mt-5 mb-3 text-muted">© Grupo de desarrollo 5 2021</p>
        </div>
                


    </body>
</html>
