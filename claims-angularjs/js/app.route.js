var app = angular.module('claimsApp', [
    'ngRoute',
    'courtModule',
    'loginModule'
]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/court', {templateUrl: 'pages/court.html', controller: 'courtController'}).
    when('/not-found', {templateUrl: 'pages/not-found.html'}).
    when('/login', {templateUrl: 'auth/login.html', controller: 'LoginController'}).
    otherwise({
        redirectTo: '/not-found'
    });
}])
.run(function($rootScope){
    $rootScope.$on('$locationChangeStart', function(){
       var token = localStorage.getItem('Authorization') || '';
       if(!token){
        window.location.href = '/index.html#!/login';
       }
    })
})