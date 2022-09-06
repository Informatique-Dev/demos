var app = angular.module('claimsApp', [
    'ngRoute',
    'courtModule',
    'loginModule'
]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/court', {templateUrl: 'pages/court.html', controller: 'courtController'}).
    when('/login', {templateUrl: 'auth/login.html', controller: 'LoginController'}).
    otherwise({
        redirectTo: '/login'
    });
}])