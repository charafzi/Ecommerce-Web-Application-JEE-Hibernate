<%@page import="dto.ClientDTO"%>
<%@ page import="dto.AchatDTO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<%
        AchatDTO achat = (AchatDTO) session.getAttribute("achat");

%>
 <!-- Navbar -->
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="container-fluid">
        <a
          href="CommandeControleur"
          class="navbar-brand"
          style="font-family: 'Goudy Old Style'; font-weight: bold"
          >Electro.ma</a
        >
      </div>

      <div class="container-fluid">
        <ul class="navbar-nav d-flex flex-row" style="margin-left: 650px;">
          <!-- Icons -->
          <li class="nav-item me-4 me-lg">
            <a class="nav-link" href="panier.jsp">
              <i class="fas fa-lg fa-shopping-cart position-relative">
                <span
                  class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger"
                  style="font-size: 0.5rem"
                  ><c:if test="${! empty achat.lgAchats }"><%= achat.getLgAchats().size() %></c:if></span
                >
              </i>
            </a>
          </li>
          
          <% ClientDTO client = (ClientDTO) session.getAttribute("client");
          
          if(client != null && client.getIdclient() != 0)
          {
          
          %>
          
          <!-- Icon dropdown -->
          <li class="nav-item me-4 me-lg dropdown" style="margin-top: 25px">
            <a
              href="#"
              class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
              id="dropdownUser1"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <img
                src="https://github.com/mdo.png"
                alt="hugenerd"
                width="30"
                height="30"
                class="rounded-circle"
              />
            </a>
            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark text-small shadow" >
	              <li>
	                <span class="d-none d-sm-inline mx-1"
	                  >	
	                  		<%= client.getNom() +" "+ client.getPrenom() %>
	                    
	
	                </span
	                >
	              </li>
	              <li>
	                <hr class="dropdown-divider" />
	              </li>
	              <li>
	              <form method="post" action="CommandeControleur">
	              	<button type="submit" name="action" value="logout"  class="btn btn-xs btn-danger">déconnecter</button>
	              
	              </form>
	              </li>
	            </ul>
	         	 </li>
          
          <%} %>
          
        </ul>
      </div>
    </nav>
    <!-- Navbar -->