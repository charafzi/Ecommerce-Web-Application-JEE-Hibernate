<%@page import="service.ProduitService"%>
<%@page import="dto.ProduitDTO"%>
<%@page import="service.CategorieService"%>
<%@page import="dto.CategorieDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%
	String admin = (String) session.getAttribute("admin");
	if(admin == null)
	{
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/loginAdmin.jsp");
		disp.forward(request,response);
	}
	List<CategorieDTO> categories = (List<CategorieDTO>) new CategorieService().retreive(); 
	List<ProduitDTO> produits = (List<ProduitDTO>) new ProduitService().retreive();

%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
 <%@ include file="resources/css.jsp" %>
</head>
<body>
	<%@include file="resources/navbar.jsp" %>
	<c:if test="${! empty error}">
				<div class="alert alert-danger text-center">
	       			 <%= request.getAttribute("error") %>
	    		</div>

			</c:if>
			<c:if test="${! empty success}">
				<div class="alert alert-success text-center">
	       			 <%= request.getAttribute("success") %>
	    		</div>

			</c:if>
	<br>
	
	<div class="container admin-cards">
		<div>
			<div class="row">
				<div class="col">
					<div class="card text-bg-secondary ">
		  			<div class="card-header">Total des produits</div>
		  			<div class="card-body">
		  			  <h5 class="card-title"><%= produits.size() %></h5>
		  			  <p class="card-text">Produits</p>
		  			</div>
					</div>
				</div>
				<div class="col">
					<div class="card text-bg-secondary">
				  <div class="card-header">Total des categories</div>
				  <div class="card-body">
				    <h5 class="card-title"><%= categories.size() %></h5>
				    <p class="card-text">Catégories</p>
				  </div>
				</div>	
				</div>
			</div> 
		</div>	
	</div>
	
	<div class="container">
	<div class="row">
	
	
		<div class="col">
		
						<!-- Button trigger modal -->
				<div class="addpcontainer"> 
				<button type="button" style="padding: 20px; margin: 20px;" id="addbtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#productModal">
				  Ajouter Produit
				</button>
				<div class="modal fade" id="productModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header" style="background: black; color:#fff;">
				        <h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter un Produit</h1>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				       <form action="AdminControleur" method="post" >
				      		<input type="hidden" name="operation" value="addproducts"/>
							 <div class="form-group">
							    Nom du produit:<input type="text" class="form-control" name="pname"  placeholder="Enter nom produit" required>
							  </div>
				
							  <div class="form-group">
							   Designation :<textarea  style="height: 100px;" class="form-control" name="pdesc" placeholder="Enter designation produit" required ></textarea>
							  </div>
							  <div class="form-group">
							    Prix:<input type="number" class="form-control" name="pprice"  placeholder="Enter prix de produit en DH" required>
							  </div>
							  
							   <div class="form-group">
							    Quantité du Stock:<input type="number" class="form-control" name="pqte"  placeholder="Enter quantité du produit" required>
							  </div>
							 
							<% 
								
							%>
							<div class="form-group">
								
								<select type="number" name="catsid" class="form-control">
								<%
								  for(CategorieDTO c: categories){
									%>
									<option value=" <%= c.getIdcat() %>"><%= c.getNom() %></option>
									<%
									}
									%>
								
								</select>
								</div>
							 
							 <div class="from-group">
							 	Photo du produit :<br><input type="file" name="ppic"/>
							 </div>
							 <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer </button>
				        <button type="submit" class="btn btn-primary" name="action" value="produit">Enregistrer</button>
				      </div>
						</form>
				      </div>
				      
				    </div>
				  </div>
				</div>
				
				</div>
		
		</div>
		
		<div class="col">
                   <button type="button" style="padding: 20px; margin: 20px;" id="addbtn" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal3">
					  Supprimer Produit
					</button>
					<div class="modal fade" id="exampleModal3" tabindex="-1" aria-labelledby="exampleModal3Label" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header" style="background: black; color:#fff;">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Supprimer une catégorie</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      
					      <form action="AdminControleur" method="post">
					      <input type="hidden" name="operation" value="addcategory"/>
								 <div class="form-group">
								    Nom Produit
								    <select name="produit">
								    <% for(ProduitDTO p:produits){ %>
								    <option value="<%= p.getIdprod() %>"> <%= p.getNom() %></option>
								    <%} %>
								    </select>
								  </div>
								  <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
					        <button type="submit" class="btn btn-danger" name="action" value="suppProd">Supprimer</button>
					      </div>
							</form>
					      </div>
					     
					    </div>
					  </div>
					</div>
                </div>
		
		<div class="col">
		
			<div class="addpcontainer"> 
			
				<div class="row">
                <div class="col">
                
                	<button type="button" style="padding: 20px; margin: 20px;" id="addbtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
					  Ajouter Catégorie
					</button>
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header" style="background: black; color:#fff;">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Ajouter une catégorie</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      
					      <form action="AdminControleur" method="post">
					      <input type="hidden" name="operation" value="addcategory"/>
								 <div class="form-group">
								    Nom categorie:<input type="text" class="form-control" name="ctitle"  placeholder="Enter nom catégorie" required>
								  </div>
								  <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
					        <button type="submit" class="btn btn-primary" name="action" value="categorie">Enregistrer</button>
					      </div>
							</form>
					      </div>
					     
					    </div>
					  </div>
					</div>
                   
                </div>
                <div class="col">
                   <button type="button" style="padding: 20px; margin: 20px;" id="addbtn" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal2">
					  Supprimer Catégorie
					</button>
					<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModal2Label" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header" style="background: black; color:#fff;">
					        <h1 class="modal-title fs-5" id="exampleModalLabel">Supprimer une catégorie</h1>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					      
					      <form action="AdminControleur" method="post">
					      <input type="hidden" name="operation" value="addcategory"/>
								 <div class="form-group">
								    Nom catégorie
								    <select name="categorie">
								    <% for(CategorieDTO c:categories){ %>
								    <option value="<%= c.getIdcat() %>"> <%= c.getNom() %></option>
								    <%} %>
								    </select>
								  </div>
								  <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
					        <button type="submit" class="btn btn-danger" name="action" value="suppCat">Supprimer</button>
					      </div>
							</form>
					      </div>
					     
					    </div>
					  </div>
					</div>
                </div>
            </div>
			
			
			</div>
		
		</div>
	
	</div>
	</div>

</body>
</html>