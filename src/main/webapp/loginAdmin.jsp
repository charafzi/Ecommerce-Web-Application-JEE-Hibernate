<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="resources/css.jsp" %>
<title>Se connecter</title>
</head>
<body>
<%@ include file="resources/navbar.jsp" %>
	<c:if test="${! empty errorMessage}">
		<div class="alert alert-danger " ="alert">
	        <%= request.getAttribute("errorMessage") %>
	    </div>

	</c:if>
	

    
    <form action="AdminControleur" method="post">
    <section class="vh-100 gradient-custom">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-12 col-md-8 col-lg-6 col-xl-5">
            <div class="card bg-dark text-white" style="border-radius: 1rem">
              <div class="card-body p-5 text-center">
                <div class="mb-md-5 mt-md-4 pb-5">
                  <h2 class="fw-bold mb-2 text-uppercase">Login Admin</h2>
                  <p class="text-white-50 mb-5">
                    Veuillez entrer votre Email et mot de passe!
                  </p>

                  <label class="form-label" for="typeEmailX">Email</label>
                  <div class="form-outline form-white mb-4">
                    <input
                      type="email"
                      name="email"
                      id="typeEmailX"
                      class="form-control form-control-lg"
                    />
                    
                  </div>

                  <label class="form-label" for="typePasswordX"
                  >Mot de passe</label
                >
                  <div class="form-outline form-white mb-4">
                    <input
                    name="password"
                      type="password"
                      id="typePasswordX"
                      class="form-control form-control-lg"
                    />
                   
                  </div>

                  <button
                    class="btn btn-outline-light btn-lg px-5"
                    type="submit"
                    name="action"
                    value="login"
                  >
                    Se connecter
                  </button>

                  
                </div>
                <i class="fa-regular fa-user"></i>
                
                <div>
                  <p class="mb-0">
                    Vous n'avez pas un compte?
                    <a href="signup.jsp">
                  <span class="ms-1 d-none d-sm-inline">Créer compte</span>
                  </a>
                
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    </form>



</body>
</html>