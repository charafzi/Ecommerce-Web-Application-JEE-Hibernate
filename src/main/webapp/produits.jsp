<%@page import="java.util.ArrayList"%>
<%@page import="dto.ProduitDTO"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
 <head>
 <%@ include file="resources/css.jsp" %>
 
    <title>Produits</title>
  </head>
<body>
<%
        ArrayList<ProduitDTO> produits = (ArrayList<ProduitDTO>) request.getAttribute("produits");
		if(produits == null )
		{
			RequestDispatcher disp = request.getServletContext().getRequestDispatcher("/CommandeControleur");
			disp.forward(request, response);
		}

   
%>
    <%@ include file="resources/navbar2.jsp" %>
    
    <div class="container-fluid">
      <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
          <div
            class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100"
          >  
     		    
			    <c:if test="${! empty categories }">
			    <div class="list-group w-75">
				    <a href="#" class="list-group-item list-group-item-action active text-center" style="background-color:#212529; border: 1px solid gray; ">
				        Catégories
				    </a> 
			    <form action="ProduitControleur" method="post">
            	<c:forEach items="${categories}" var="cat">
            	
		            	 <button type="submit" class="list-group-item list-group-item-action" name="categorie" value="${cat.idcat}">   
               		   <span class="ms-1 d-none d-sm-inline fs-6 text-center"><c:out value="${cat.nom }" /></span>
              			 </button>
            		
            	</c:forEach>
               </form>
               </div>
            </c:if>
          </div>
        </div>

        <div class="col py-3">
        	<form action="ProduitControleur" method="get" class="form-inline d-flex justify-content-center md-form form-sm mt-0">
        	 	<i class="fas fa-search" aria-hidden="true" style="margin: 8px 10px 0px 0px;"></i>
       			<input class="form-control form-control-sm ml-5 w-25" type="text" placeholder="Chercher un produit"
         				 aria-label="Search" name="motcle" >
     	    </form>
     	    <c:if test="${! empty error}">
				<div class="alert alert-danger text-center" style="margin-top: 10px;">
	       			 <%= request.getAttribute("error") %>
	    		</div>

			</c:if>
			<c:if test="${! empty success}">
				<div class="alert alert-success text-center" style="margin-top: 10px;">
	       			 <%= request.getAttribute("success") %>
	    		</div>

			</c:if>
     	    
     	    <c:choose>
          		
          	
          	
          	
	          	<c:when test="${! empty produits}">
	          			<div class="container" style="display: flex; flex-wrap: wrap">
							<c:forEach items="${produits}" var="p">
							<div class="card border-light" style="margin: 25px; width: 550px;">
								  <!-- card -->
								  <div class="card border-light">
								    <div
								      class="text-center"
								      style="max-width: 250px; max-height: 200px; margin: auto"
								    >
								      <img
								        src='<c:out value="${p.imageurl}" />'
								        alt="..."
								        class="img-responsive"
								        style="max-width: 100%; max-height: 100%; width: auto; height: auto"
								      />
								    </div>
								    <div class="card-footer border-top border-light p-4">
								      <div class="d-flex mt-2">
								        <h6 style="color: #212529; font-weight: bold; font-size: 16px;">
								          <span class="label label-default"><c:out value="${p.nom}" /></span>
								        </h6>
								      </div>
								      
								      <div class="row">
									      <div class="col-10">
									     	 <h6 style="color: red; font-size: 12px;">
									     	 <span class="label label-default" style="font-weight: bold;"><c:out value="${p.stock}"/></span>
								          		articles restants
								       		 </h6>
									      
									      </div>
									      
								      </div>
								      <div class="row">
									      <div class="col-10">
									     	 <h6 style="font-weight: bold;">
									     	 <span class="label label-default"><c:out value="${p.prix}"/>Dh</span>
								       		 </h6>
									      
									      </div>
									      
								      </div>
								
								      <div class="row">
								        <div class="col">
								        <form action="CommandeControleur" method="post">
								          <input
								            type="hidden"
								            name="idprod"
								            value='<c:out value="${p.idprod}" />'
								          />
								          <button
								            type="submit"
								            name="action"
								            value="ajouter"
								            class="btn btn-xs btn-primary"
								            style="margin: 10px; width: 100%"
								            href="#"
								          >
								            <span class="fas fa-cart-plus mr-2"></span> Ajouter
								          </button>
								        </form>
								        </div>
								      </div>
								    </div>
								  </div>
								  <!-- card -->
								</div>
							
			           
			      		   </c:forEach>
			        	</div>
			    </c:when>
          		<c:otherwise>
          			<span class="alert alert-warning d-flex justify-content-center" style="margin-top: 20px;">Aucun produit est disponible</span>
          		</c:otherwise>	
          	</c:choose>
            
        
        </div>
      </div>
    </div>
    
  </body>

</html>