var claimApp = angular.module('courtModule', ['angularUtils.directives.dirPagination'])

claimApp.controller('courtController', function ($scope, $http){
    $scope.court = {}
    $scope.token = localStorage.getItem('Authorization') || '';

    //get govarnment
    $scope.getGov = function() {
        $http.get("http://41.38.83.137:8085/governorate?size=30&page=0", {
            headers: { Authorization: `Bearer ${$scope.token}` }
          }).then(function(mydata){
            $scope.govs = mydata.data.data
        })
    }

    //get court
    $scope.getCourt = function(gov){
        $http.get(`http://41.38.83.137:8085/governorate/${gov.id}/court?page=0&size=30`, {
            headers: { Authorization: `Bearer ${$scope.token}` }
          }).then(function(mydata){
            $scope.courts = mydata.data.data
        })
    }

    //save(add,update)
    $scope.save = function(court, gov, formNotValid) {
        if(!court.id) $scope.add(court, gov, formNotValid)
        else $scope.update(court, gov)
    };

    //add
    $scope.add = function(court, gov, formNotValid) {
        if(!court.enabled){
            court.enabled = false;
        }
        if(!formNotValid) {
            $http.post(`http://41.38.83.137:8085/governorate/${gov.id}/court`, court, {
                headers: { Authorization: `Bearer ${$scope.token}` }
                }).then(function(){
                $scope.getCourt(gov)
                $scope.reset()
            })
        }
    };

    //fetch
    $scope.fetch = function(currentCourt) {
        $scope.court.id = currentCourt.id
        $scope.court.arabicName = currentCourt.arabicName
        $scope.court.englishName = currentCourt.englishName
        $scope.court.code = currentCourt.code
        $scope.court.enabled = currentCourt.enabled
    };

    //update
    $scope.update = function(court, gov) {
        $http.put(`http://41.38.83.137:8085/court/${court.id}`, court, {
            headers: { Authorization: `Bearer ${$scope.token}` }
            }).then(function(){
            $scope.getCourt(gov)
            $scope.reset()
        })
    };

    //delete
    $scope.deleteCourt = function(id, gov){
        $http.delete(`http://41.38.83.137:8085/court/${id}`, {
            headers: { Authorization: `Bearer ${$scope.token}` }
            }).then(function(){
            $scope.getCourt(gov)
        })
    }

    //reset form
    $scope.reset = function() {
        $scope.court.id = ''
        $scope.court.arabicName = ''
        $scope.court.englishName = ''
        $scope.court.code = ''
        $scope.court.enabled = ''
    };
})