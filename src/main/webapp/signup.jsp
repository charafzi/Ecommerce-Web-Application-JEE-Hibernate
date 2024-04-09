<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="resources/css.jsp" %>
<title>Créer un compte</title>
</head>
<body>
<%@ include file="resources/navbar.jsp" %>
	<c:if test="${! empty message }}">
	<div class="alert alert-danger text-center" ="alert">
        <%= request.getAttribute("message") %>
    </div>
	<br/>
	</c:if>
    
    <form action="SignUpServlet" method="post" >
      <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
          <div
            class="row d-flex justify-content-center align-items-center h-100"
          >
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
              <div class="card bg-dark text-white" style="border-radius: 1rem">
                <div class="card-body p-5 text-center">
                  <div class="mb-md-5 mt-md-4 pb-5">
                    <h2 class="fw-bold mb-2 text-uppercase">Création du compte</h2>
                    <p class="text-white-50 mb-5">
                     	Entrer toutes les informations ci-dessous
                    </p>

                    <div class="mb-4">
                      <label for="typeFirstName" class="form-label"
                        >Prenom</label
                      >
                      <input
                        type="text"
                        name="prenom"
                        id="typeFirstName"
                        class="form-control form-control-lg"
                        required
                      />
                    </div>

                    <div class="mb-4">
                      <label for="typeLastName" class="form-label"
                        >Nom</label
                      >
                      <input
                        type="text"
                        name="nom"
                        id="typeLastName"
                        class="form-control form-control-lg"
                        required
                      />
                    </div>

                    <div class="mb-4">
                      <label for="typeEmail" class="form-label">Email</label>
                      <input
                        type="email"
                        name="email"
                        id="typeEmail"
                        class="form-control form-control-lg"
                        required
                      />
                    </div>

                    <div class="mb-4">
                      <label for="typePassword" class="form-label"
                        >Mot de passe</label
                      >
                      <input
                        type="password"
                        name="password"
                        id="typePassword"
                        class="form-control form-control-lg"
                        required
                      />
                    </div>

                    <button
                      type="submit"
                      name="action"
                      value="signup"
                      class="btn btn-outline-light btn-lg px-5"
                    >
                      Créer compte
                    </button>
                  </div>
                  

                  <div>
                    
				</form>
				<i class="fa-regular fa-user"></i>
				<a href='<c:url value="login.jsp"/>'  class="text-white-50 fw-bold">Se connecter</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>


</body>
</html>