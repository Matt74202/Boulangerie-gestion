<%@page import="java.util.Vector"%>
<%@page import="model.Genre"%>
<%@page import="model.Commission"%>
<% 
    Vector<Commission> commissions= (Vector<Commission>) request.getAttribute("commissions"); 
   

    Vector<Genre> genres=(Vector<Genre>) request.getAttribute("genres"); 

    
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
                        <h2 class="text-center mb-4">Liste Commission par Genre</h2>
                
                        <!-- Formulaire de recherche -->
                        <form action="./CommissionGenre" method="GET" class="p-4 border rounded shadow-sm">
                            <div class="row g-3 mb-3">
                                <div class="mb-3">
                                    <label for="produit" class="form-label">Vendeur</label>
                                    <select id="produit" name="genre" class="form-select">
                                    <option value="0">Genre</option>
                                            <% for (Genre ta : genres) { %>
                                    <option value="<%= ta.getIdGenre() %>"><%= ta.getNomGenre() %></option>
                            <% } %>
                           
                                </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="mois" class="form-label">Min</label>
                                    <input type="date" id="mois" name="date1" class="form-control"   >
                                </div>
                                <div class="col-md-6">
                                    <label for="annee" class="form-label">Max</label>
                                    <input type="date" id="annee" name="date2" class="form-control" >
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Rechercher</button>
                        </form>
                
                        <!-- Tableau pour afficher les résultats -->
                        <div class="mt-5">
                            <h4 class="mb-3">Résultats</h4>
                            <table class="table table-striped table-bordered">
                                <thead class="table-dark">
                                     <% for (Commission c : commissions) { %>
                                    <tr>
                                       <td><%= c.getGenre().getNomGenre() %></td>
                                       <td><%= c.getSomme() %></td> 
                                    </tr>
                         <% } %>
                                </thead>
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