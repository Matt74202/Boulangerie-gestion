<%@page import="java.util.Vector" %>
<%@page import="model.Produit" %>
<%@page import="model.Client" %>
<%@page import="model.Vente" %>

<%
    Vector<Vente> ventes = (Vector<Vente>) request.getAttribute("ventes");
    Vector<Client> clients= (Vector<Client>) request.getAttribute("clients");
%>



<!DOCTYPE html>
<html lang="en">
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
   <body class="dashboard dashboard_1">
      <div class="full_container">
         <div class="inner_container">
            <nav id="sidebar">
               <div class="sidebar_blog_1">
                  <div class="sidebar_user_info">
                     <div class="icon_setting"></div>
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
               <div class="topbar">
                  <nav class="navbar navbar-expand-lg navbar-light">
                     <div class="full">
                        <button type="button" id="sidebarCollapse" class="sidebar_toggle"><i class="fa fa-bars"></i></button>
                        <div class="logo_section">
                        </div>
                        <div class="right_topbar">
                           <div class="icon_info">
                              <ul>
                                 <li><a href="#"><i class="fa fa-bell-o"></i><span class="badge">2</span></a></li>
                                 <li><a href="#"><i class="fa fa-question-circle"></i></a></li>
                                 <li><a href="#"><i class="fa fa-envelope-o"></i><span class="badge">3</span></a></li>
                              </ul>
                              <ul class="user_profile_dd">
                                 <li>
                                    <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="images/layout_img/user_img.jpg" alt="#" /><span class="name_user">John David</span></a>
                                    <div class="dropdown-menu">
                                       <a class="dropdown-item" href="profile.html">My Profile</a>
                                       <a class="dropdown-item" href="settings.html">Settings</a>
                                       <a class="dropdown-item" href="help.html">Help</a>
                                       <a class="dropdown-item" href="#"><span>Log Out</span> <i class="fa fa-sign-out"></i></a>
                                    </div>
                                 </li>
                              </ul>
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
                              <h2>Dashboard</h2>
                           </div>
                        </div>
                     </div>
                      
                     <div class="container mt-5">
                        <h2 class="text-center mb-4">Liste Vente Client</h2>
                
                        <form action="./ListeVenteController" method="post" class="p-4 border rounded shadow-sm">
                            <div class="mb-3">
                                <label for="produit" class="form-label">Client</label>
                                <select id="produit" name="client" class="form-select">
                                    <option value="0">Client</option>
                                            <% for (Client ta : clients) { %>
                                    <option value="<%= ta.getId() %>"><%= ta.getNom() %> <%= ta.getPrenom() %></option>
  <% } %>
                           
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="produit" class="form-label">Date</label>
                                <input type="date" class="form-control" name="date">
                            </div>

                            <button type="submit" class="btn btn-primary w-100">Rechercher</button>

                        </form>
                
                        <div class="mt-5">
                            <h4 class="mb-3">Résultats de la Recherche</h4>
                            <table class="table table-striped table-bordered">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Id</th>
                                        <th>Produit</th>
                                        <th>Quantite</th>
                                        <th>Client</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                      <% for (Vente v : ventes) { %>
                                    <tr>
                                       <td><%= v.getIdVente() %></td>
                                       <td><%= v.getProduit().getNom() %></td> 
                                       <td><%= v.getQuantite() %></td>
                                       <td><%= v.getClient().getNom() %> <%= v.getClient().getPrenom() %></td>
                                       <td><%= v.getDate() %></td>
                                    </tr>
                         <% } %>
                                   
                                </tbody>
                            </table>
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