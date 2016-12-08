<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>GramR photo service</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/business-casual.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

</head>

<body>

<div class="brand">GramR</div>
<div class="address-bar">by Rick van Lieshout</div>

<!-- Navigation -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
            <a class="navbar-brand" href="index.html">GramR</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        Photo <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/photos?user=Mastermindzh">Photo's</a></li>
                        <li><a href="/addphoto?user=Mastermindzh">Add photo</a></li>
                        <li><a href="/addphototoset?user=Mastermindzh">Add photo to set</a></li>
                        <li><a href="/rest/photos/Mastermindzh">PHOTO_JSON</a></li>
                    </ul>
                </li>

                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        Sets <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/sets?user=Mastermindzh">Sets</a></li>
                        <li><a href="/addset">Add set</a></li>
                        <li><a href="/editset">Edit set</a></li>
                        <li><a href="/rest/sets/Mastermindzh">SET_JSON</a></li>
                    </ul>
                </li>
                <div class="col-sm-5 col-md-5 pull-right" style = "padding: 20px !important;">
                    <form class = "navbar-form" method="get" action="/rest/photos/Mastermindzh/" onsubmit="return false;">
                        <div class="input-group">
                            <input class="form-control" type="search" name="q" value="" placeholder="Seach..." />
                            <div class="input-group-btn">
                                <input class="btn btn-default" type="submit" onclick="window.location.href=this.form.action + this.form.q.value;" value = "go!"/>
                            </div>
                        </div>
                    </form>
                </div>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<div class="container">
        <div class="box">
            <div class="col-lg-12 text-center">