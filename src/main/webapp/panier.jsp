<%@page import="dto.LigneAchatDTO"%>
<%@page import="dto.AchatDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
  <head>
       <%@ include file="resources/css.jsp" %>

    <title>Mon panier</title>
  </head>
  <body>
  
    <%@ include file="resources/navbar2.jsp" %>
   
   <div class="container-fluid">
      <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
          <div
            class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100"
          >
          </div>
        </div>

        <div class="col py-3">
          <div class="container">
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
			<c:if test="${! empty achat }">
	            <table id="cart" class="table table-hover table-condensed" style="width:100%;">
              <thead>
                <tr>
                  <th style="width:40%">Produit</th>
                  <th style="width:20%">Prix en Dh</th>
                  <th style="width:10%" align="left">Quantité</th>
                  <th style="width:20%" class="text-center">Sous total en Dh</th>
                  <th style="width:10%">Action</th>
                </tr>
              </thead>
              <tbody>
              
            	<%for(LigneAchatDTO l:achat.getLgAchats()){ %>
                <tr>
                  <td data-th="Product">
                    <div class="row">
                      <div class="col-sm-2 hidden-xs"><img src="<%= l.getProduit().getImageurl() %>"  alt="..." class="img-responsive"
                      		width="100px" height="100px" />
                      </div>
                      <div class="col-sm-10">
                        <h4 class="nomargin" style="margin-left: 80px;"><%= l.getProduit().getNom() %></h4>
                        <p style="margin-left: 80px;"><%= l.getProduit().getDesignation() %></p>
                      </div>
                    </div>
                  </td>
                  <td data-th="Price"><%= l.getProduit().getPrix() %></td>
                  <td data-th="Quantity">
    					<div class="input-group">
    					 <form action="CommandeControleur" method="post">
							    <button type="submit" name="decrementer" value="<%= l.getProduit().getIdprod() %>" class="btn btn-sm btn-secondary">
							        <i class="fa fa-minus"></i>
							    </button>
							</form>
							
							<input type="text" class="form-control text-center" value="<%= l.getQuantite() %>" id="quantityInput" disabled="disabled">
							
							<form action="CommandeControleur" method="post">
							    <button type="submit" name="incrementer" value="<%= l.getProduit().getIdprod() %>" class="btn btn-sm btn-secondary">
							        <i class="fa fa-plus"></i>
							    </button>
							</form>   					
						</div>
					</td>
                  <td data-th="Subtotal" class="text-center"><fmt:formatNumber value="<%= l.getSousTotal() %>" pattern=".00"/></td>
                  
                  <td align="left">
                  <form action="CommandeControleur" method="post">
                  	<button  type="submit" name="supprimerLigne" class="btn btn-warning " value="<%= l.getProduit().getIdprod() %>" style="background: none; border: none; padding: 0; cursor: pointer;"><i class="fa-solid fa-trash " style="color: #ca020c;"></i></button>
                  </form>
                  </td>
                  
                </tr>
                <%} %>
              </tbody>
              <!--<tfoot>
                
                <tr>
                  <td colspan="3">
                 
                <form action="CommandeControleur" method="get">
		  			  <button type="submit" name="action" class="btn btn-info text-center"><i class="fa fa-angle-left" ></i> Continuer Shopping</button>
				</form>
				</td>
                  <td align="left"><strong>Total <fmt:formatNumber value="<%= achat.getTotal() %>" pattern=".00"/></strong>
                    
                   <% 
                   if(client != null && client.getIdclient() != 0)
                   {
                   %>
                   <button type="button" class="btn btn-xs btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                    Payer <i class="fa fa-angle-right"></i></button>
                   <%}
                   else{
                   %>
                   <a class="btn btn-xs btn-primary" href="login.jsp"> Payer <i class="fa fa-angle-right"></i></a>
                   <%} %>
                   
                   </td> 
                  <td align="left">
	                <form action="CommandeControleur" method="post">
	                <button type="submit" name="action" value="vider" class="btn btn-xs btn-danger ">Vider </button>
	                </form>
                  </td>             
                </tr>
              </tfoot>
               -->
               
            </table>
            </c:if>
            <c:if test="${empty achat }">
            	<span class="alert alert-warning d-flex justify-content-center">Panier vide</span>
            
            </c:if>
          </div>
          
          <div class="card mx-auto" style="max-width: 800px;">
		    <div class="card-body">
		        <div class="row">
		            <div class="col">
		                <form action="CommandeControleur" method="get">
		  					  <button type="submit" name="action" class="btn btn-info text-center" style="width: 100%"><i class="fa fa-angle-left" ></i> Continuer Shopping</button>
						</form>
		            </div>
		            <div class="col">
		                <form action="CommandeControleur" method="post">
	               			 <button type="submit" name="action" value="vider" class="btn btn-xs btn-danger " style="width: 100%">Vider </button>
	                	</form>
		            </div>
		            <div class="col">
		               	  <% 
		                   if(client != null && client.getIdclient() != 0)
		                   {
		                   %>
		                   <button type="button" class="btn btn-xs btn-primary" data-toggle="modal" data-target="#exampleModalCenter" style="width: 100%">
		                    Commander<c:if test="${! empty achat && achat.getLgAchats().size()!=0 }">(<fmt:formatNumber value="<%= achat.getTotal() %>" pattern=".00"/> Dh)</c:if><i class="fa fa-angle-right"></i></button>
		                   <%}
		                   else{
		                   %>
		                   <a class="btn btn-xs btn-primary" href="login.jsp"> Commander<c:if test="${! empty achat && achat.getLgAchats().size()!=0 }">(<fmt:formatNumber value="<%= achat.getTotal() %>" pattern=".00"/> Dh)</c:if><i class="fa fa-angle-right"></i></a>
                   		<%} %>
		            </div>
		
		        </div>
		    </div>
		</div>
         
         
		<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Entrer les informations de paiement</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="CommandeControleur" method="post">
        <div class="modal-body">
          <div class="form-group">
            <label for="cardNumber">Numéro de carte:</label>
            <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Entrez le numéro de carte" required>
          </div>
          <div class="form-group">
            <label for="cardExpiration">Date d'expiration:</label>
            <input type="text" class="form-control" id="cardExpiration" name="cardExpiration" placeholder="MM/AA" required>
          </div>
          <div class="form-group">
            <label for="cardExpiration">CSV</label>
            <input type="text" class="form-control" id="cardExpiration" name="csv" placeholder="123" required maxlength="3">
          </div>
          <!-- Additional payment information fields can be added here -->
           <div class="form-group">
            <label for="cardType">Type de carte:</label>
            <select class="form-control" id="cardType" name="cardType">
                <option value="Visa">Visa</option>
                <option value="Mastercard">Mastercard</option>
                <option value="American Express">American Express</option>
                <!-- Ajoutez d'autres options si nécessaire -->
            </select>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
          <button type="submit" name="action" value="payer" class="btn btn-primary">Valider</button>
        </div>
      </form>
    </div>
  </div>
</div>	

    </div>
  </body>
</html>
