<%@page import="java.util.Vector"%>

<%@page import="model.Produit"%>
<%@page import="model.Client"%>
<%@page import="model.Vendeur"%>

<% 
    Vector<Produit> produits= (Vector<Produit>) request.getAttribute("produits"); 
   Vector<Client> clients= (Vector<Client>) request.getAttribute("clients");
   Vector<Vendeur> vendeurs=(Vector<Vendeur>) request.getAttribute("vendeurs");

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
                    <%-- liste  --%>

                    <div class="container d-flex justify-content-center mt-5">
                     <form action="./InsertVenteServlet" method="POST" class="needs-validation" novalidate>
                         <%-- select 1 --%>
                         <div class="mb-3">
                             <label for="categorie" class="form-label">Produit</label>
                             <select class="form-select" id="categorie" name="produit" required>
                              <%  
                                 for (int i  = 0 ; i < produits.size() ; i++){ %>
                                     <option value="<%= produits.get(i).getId()  %>"><%= produits.get(i).getNom()  %></option>
                             <%    }
                                 %>
                             </select>
                         </div>
                         <div class="mb-3">
                           <label for="client" class="form-label">Client</label>
                           <select class="form-select" id="client" name="client" required>
                            <%  
                               for (int i  = 0 ; i < clients.size() ; i++){ %>
                                   <option value="<%= clients.get(i).getId()  %>"><%= clients.get(i).getNom() %> <%= clients.get(i).getPrenom() %></option>
                           <%    }
                               %>
                           </select>
                        </div>
                           <div class="mb-3">
                           <label for="client" class="form-label">Vendeur</label>
                           <select class="form-select" id="client" name="vendeur" required>
                            <%  
                               for (int i  = 0 ; i < vendeurs.size() ; i++){ %>
                                   <option value="<%= vendeurs.get(i).getId()  %>"><%= vendeurs.get(i).getNom() %> <%= vendeurs.get(i).getPrenom() %></option>
                           <%    }
                               %>
                           </select>
                        </div>
                        <div class="mb-3">
                           <label for="date" class="form-label">Date vente</label>
                           <input type="date" name="date">
                        </div>
                         <%-- select2 --%>
                         <div class="mb-3">
                             <label for="categorie" class="form-label">Quantité</label>
                             <input type="number" name="quantite">
                         </div>
                         <div class="text-center">
                             <button type="submit" class="btn btn-primary">Soumettre</button>
                         </div>
                     </form>
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