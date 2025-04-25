<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire avec Redirection</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="styles.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h4 class="text-center mb-0">Formulaire avec Redirection</h4>
                    </div>
                    <div class="card-body">
                        <form action="./ListeProduitServlet" method="get">
                            <p class="text-center">Choisissez une action :</p>
                            <div class="d-grid gap-2">
                                <button type="submit" name="action" value="page1" class="btn btn-outline-primary">Liste des produits</button>
                                <button type="submit" name="action" value="page2" class="btn btn-outline-secondary">Bisous</button>
                                <button type="submit" name="action" value="page3" class="btn btn-outline-success">Je t'aime</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS (optional, for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
