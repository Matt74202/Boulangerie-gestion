<%@page import="model.PrixProduit"%>
<%@page import="model.Produit"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Price History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <title>Boulangerie</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <link rel="icon" href="images/fevicon.png" type="image/png" />
      <link rel="stylesheet" href="./assets/css/bootstrap.min.css" />
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
      <link rel="stylesheet" href="./assets/style.css" />
      <link rel="stylesheet" href="./assets/css/responsive.css" />
      <link rel="stylesheet" href="./assets/css/colors.css" />
      <link rel="stylesheet" href="./assets/css/bootstrap-select.css" />
      <link rel="stylesheet" href="./assets/css/perfect-scrollbar.css" />
      <link rel="stylesheet" href="./assets/css/custom.css" />
   </head>
</head>
<body class="dashboard dashboard_1">
    <div class="full_container">
        <div class="inner_container">
            <nav id="sidebar">
                <!-- Same sidebar as addProduit.jsp -->
                <div class="sidebar_blog_1">
                    <div class="sidebar_user_info">
                        <div class="user_profle_side">
                            <div class="user_img"><img class="img-responsive" src="images/layout_img/user_img.jpg" alt="#" /></div>
                            <div class="user_info">
                                <h6>Admin</h6>
                                <p><span class="online_animation"></span> Online</p>
                            </div>
                        </div>
                    </div>
                </div>
                <%@ include file="/sidebar.jsp" %>
            </nav>
            <div id="content">
                <!-- Same topbar as addProduit.jsp -->
                <div class="topbar">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <div class="full">
                            <button type="button" id="sidebarCollapse" class="sidebar_toggle"><i class="fa fa-bars"></i></button>
                            <div class="right_topbar">
                                <div class="icon_info">
                                    <!-- Topbar content -->
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
                <div class="midde_cont">
                    <div class="container-fluid">
                        <div class="row column_title">
                            <div class="col-md-12">
                                <div class="page_title">
                                    <h2>Price History Search</h2>
                                </div>
                            </div>
                        </div>
                        <div class="container mt-5">
                            <form action="HistoriqueController" method="POST" class="p-4 border rounded shadow-sm">
                                <div class="mb-3">
                                    <label class="form-label">Product:</label>
                                    <select name="idProduit" class="form-select">
                                        <option value="">All Products</option>
                                        <% Vector<Produit> produits = (Vector<Produit>) request.getAttribute("produits"); 
                                           if (produits != null) {
                                               for (Produit p : produits) { %>
                                                   <option value="<%= p.getId() %>"><%= p.getNom() %></option>
                                        <%   }
                                           } %>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Date:</label>
                                    <input type="date" name="date" class="form-control">
                                </div>
                                <button type="submit" class="btn btn-primary">Search</button>
                            </form>

                            <%-- Results Table --%>
                            <% Vector<PrixProduit> results = (Vector<PrixProduit>) request.getAttribute("results"); 
                               if (results != null && !results.isEmpty()) { %>
                                   <table class="table table-striped mt-4">
                                       <thead>
                                           <tr>
                                               <th>Product</th>
                                               <th>Price</th>
                                               <th>Date</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                                           <% for (PrixProduit pp : results) { %>
                                               <tr>
                                                   <td><%= pp.getProduit().getNom() %></td>
                                                   <td><%= pp.getPrix() %></td>
                                                   <td><%= pp.getDate() %></td>
                                               </tr>
                                           <% } %>
                                       </tbody>
                                   </table>
                            <% } else if (results != null) { %>
                                <p class="mt-4">No records found.</p>
                            <% } %>

                            <script>
                                const urlParams = new URLSearchParams(window.location.search);
                                if (urlParams.has('error')) {
                                    alert("Error: " + decodeURIComponent(urlParams.get('error')));
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/js/jquery.min.js"></script>
      <script src="./assets/js/popper.min.js"></script>
      <script src="./assets/js/bootstrap.min.js"></script>
      <script src="./assets/js/animate.js"></script>
      <script src="./assets/js/bootstrap-select.js"></script>
      <script src="./assets/js/owl.carousel.js"></script> 
      <script src="./assets/js/Chart.min.js"></script>
      <script src="./assets/js/Chart.bundle.min.js"></script>
      <script src="./assets/js/utils.js"></script>
      <script src="./assets/js/analyser.js"></script>
      <script src="./assets/js/perfect-scrollbar.min.js"></script>
      <script>
         var ps = new PerfectScrollbar('#sidebar');
      </script>
      <script src="./assets/js/custom.js"></script>
      <script src="./assets/js/chart_custom_style1.js"></script>
</body>
</html>