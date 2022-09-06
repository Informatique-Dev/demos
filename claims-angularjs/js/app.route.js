var app = angular.module('claimsApp', [
    'ngRoute',
    'loginModule'
]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/login', {templateUrl: 'auth/login.html', controller: 'LoginController'}).
    otherwise({
        redirectTo: '/login'
    });
}])