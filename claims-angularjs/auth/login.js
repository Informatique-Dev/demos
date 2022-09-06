var app = angular.module('loginModule', [])

app.controller('LoginController', function ($scope, $http){
    $scope.login = function(userName, password){
        $http.post(`http://41.38.83.137:8085/auth/login`, {username: userName, password: password})
        .then(function(token){
            localStorage.setItem('Authorization', token.data.accessToken);
            window.location.href = 'index.html#!/court';
        })
    }
})