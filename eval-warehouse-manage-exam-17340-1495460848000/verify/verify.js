//
// Generated from RAML specification
// <https://github.com/aisensiy/raml2test>
//

var request = require('request');
var chai = require('chai');
var assert = chai.assert;
var tv4 = require('tv4');
var endpoint = "http://localhost:8088";

console.log(endpoint);

var wareHouseId, wareHouseURI, containerId, containerURI;

describe("Test", function () {
  this.timeout(60000);
  step("POST /containers -> 201", function (done) {
    var options = {
      url: endpoint + '/containers',
      method: 'POST',
      qs: {},
      json: {
        "id": 4
      },
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 201, "Expect 201, got " + response.statusCode);
      containerURI = response.headers['location'];
      var splits = containerURI.split("/");
      containerId = splits[splits.length - 1];
      console.log(containerURI);
      done();
    });
  });
  
  step("GET /containers -> 200", function (done) {
    var options = {
      url: containerURI,
      method: 'GET',
      qs: {},
      body: null,
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 200, "Expect 200, got " + response.statusCode);
      var schema = {
        "$schema": "http://json-schema.org/draft-04/schema#",
        "type": "object",
        "required": true,
        "totalCount": {
          "type": "integer",
          "required": true
        },
        "containers": {
          "type": "array",
          "items": {
            "type": "object",
            "properties": {
              "id": {
                "type": "integer",
                "required": true
              }
            }
          }
        }
      };
      if (schema != '') {
        // verify response body
        body = (body == '' ? '[empty]' : body);
        assert.doesNotThrow(function () {
          JSON.parse(body);
        }, JSON.SyntaxError, "Invalid JSON: " + body);
        var json = JSON.parse(body);
        var result = tv4.validateResult(json, schema);
        assert.lengthOf(result.missing, 0, "Missing/unresolved JSON schema $refs (" + result.missing && result.missing.join(', ') + ") in schema: " + JSON.stringify(schema, null, 4) + " Error");
        assert.ok(result.valid, "Got unexpected response body: " + (result.error && result.error.message) + " " + JSON.stringify(schema, null, 4) + " Error");
      }
      done();
    });
  });
  
  step("GET /containers/{containerId} -> 200", function (done) {
    var options = {
      url: endpoint + '/containers/' + containerId,
      method: 'GET',
      qs: {},
      body: null,
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 200, "Expect 200, got " + response.statusCode);
      var schema = {
        "$schema": "http://json-schema.org/draft-04/schema#",
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "required": true
          }
        }
      };
      if (schema != '') {
        // verify response body
        body = (body == '' ? '[empty]' : body);
        assert.doesNotThrow(function () {
          JSON.parse(body);
        }, JSON.SyntaxError, "Invalid JSON: " + body);
        var json = JSON.parse(body);
        var result = tv4.validateResult(json, schema);
        assert.lengthOf(result.missing, 0, "Missing/unresolved JSON schema $refs (" + result.missing && result.missing.join(', ') + ") in schema: " + JSON.stringify(schema, null, 4) + " Error");
        assert.ok(result.valid, "Got unexpected response body: " + (result.error && result.error.message) + " " + JSON.stringify(schema, null, 4) + " Error");
      }
      done();
    });
  });
  
  step("PUT /containers/{containerId} -> 204", function (done) {
    var options = {
      url: endpoint + '/containers/' + containerId,
      method: 'PUT',
      qs: {},
      json: {
        "id": 5
      },
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 204, "Expect 204, got " + response.statusCode);
      done();
    });
  });
  
  step("DELETE /containers/{containerId} -> 204", function (done) {
    var options = {
      url: endpoint + '/containers/' + containerId,
      method: 'DELETE',
      qs: {},
      body: "",
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 204, "Expect 204, got " + response.statusCode);
      done();
    });
  });
  
  
  
  step("POST /wareHouses -> 201", function (done) {
    var options = {
      url: endpoint + '/wareHouses',
      method: 'POST',
      qs: {},
      json: {
        "name": "Yellow Duck House"
      },
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 201, "Expect 201, got " + response.statusCode);
      var schema = "";
      if (schema != '') {
        // verify response body
        body = (body == '' ? '[empty]' : body);
        assert.doesNotThrow(function () {
          JSON.parse(body);
        }, JSON.SyntaxError, "Invalid JSON: " + body);
        var json = JSON.parse(body);
        var result = tv4.validateResult(json, schema);
        assert.lengthOf(result.missing, 0, "Missing/unresolved JSON schema $refs (" + result.missing && result.missing.join(', ') + ") in schema: " + JSON.stringify(schema, null, 4) + " Error");
        assert.ok(result.valid, "Got unexpected response body: " + result.error && result.error.message + " " + JSON.stringify(schema, null, 4) + " Error");
        wareHouse
      }
      wareHouseURI = response.headers['location'];
      var splits = wareHouseURI.split("/");
      wareHouseId = splits[splits.length - 1];
      done();
    });
  });
  
  step("GET /wareHouses/{wareHouseId} -> 200", function (done) {
    var options = {
      url: endpoint + '/wareHouses/' + wareHouseId,
      method: 'GET',
      qs: {},
      body: "",
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 200, "Expect 200, got " + response.statusCode);
      var schema = {
        "$schema": "http://json-schema.org/draft-04/schema#",
        "type": "object",
        "properties": {
          "id": {
            "type": "integer"
          },
          "name": {
            "type": "string"
          },
          "containers": {
            "type": "array",
            "container": {
              "type": "integer"
            }
          },
          "required": ["id", "name"]
        }
      };
      if (schema != '') {
        // verify response body
        body = (body == '' ? '[empty]' : body);
        assert.doesNotThrow(function () {
          JSON.parse(body);
        }, JSON.SyntaxError, "Invalid JSON: " + body);
        var json = JSON.parse(body);
        var result = tv4.validateResult(json, schema);
        assert.lengthOf(result.missing, 0, "Missing/unresolved JSON schema $refs (" + result.missing && result.missing.join(', ') + ") in schema: " + JSON.stringify(schema, null, 4) + " Error");
        assert.ok(result.valid, "Got unexpected response body: " + (result.error && result.error.message) + " " + JSON.stringify(schema, null, 4) + " Error");
      }
      done();
    });
  });
  
  step("GET /wareHouses -> 200", function (done) {
    var options = {
      url: endpoint + '/wareHouses',
      method: 'GET',
      qs: {},
      body: '',
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 200, "Expect 200, got " + response.statusCode);
      var schema = {
        "$schema": "http://json-schema.org/draft-04/schema#",
        "type": "object",
        "totalCount": {
          "type": "integer"
        },
        "providers": {
          "type": "array",
          "properties": {
            "id": {
              "type": "integer"
            },
            "name": {
              "type": "string"
            },
            "containers": {
              "type": "array",
              "container": {
                "type": "integer"
              }
            },
            "required": ["id", "name"]
          }
    
        }
      };
      if (schema != '') {
        // verify response body
        body = (body == '' ? '[empty]' : body);
        assert.doesNotThrow(function () {
          JSON.parse(body);
        }, JSON.SyntaxError, "Invalid JSON: " + body);
        var json = JSON.parse(body);
        var result = tv4.validateResult(json, schema);
        assert.lengthOf(result.missing, 0, "Missing/unresolved JSON schema $refs (" + result.missing && result.missing.join(', ') + ") in schema: " + JSON.stringify(schema, null, 4) + " Error");
        assert.ok(result.valid, "Got unexpected response body: " + (result.error && result.error.message) + " " + JSON.stringify(schema, null, 4) + " Error");
      }
      done();
    });
  });
  
  step("PUT /wareHouses/{id} -> 204", function (done) {
    var options = {
      url: endpoint + '/wareHouses/' + wareHouseId,
      method: 'PUT',
      qs: {},
      json:  {
        "name": "Super Apple",
        "containers": [1, 2, 4]
      },
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 204, "Expect 204, got " + response.statusCode);
      done();
    });
  });
  
  step("DELETE /wareHouses -> 204", function (done) {
    var options = {
      url: endpoint + '/wareHouses/' + wareHouseId,
      method: 'DELETE',
      qs: {},
      json: "",
      header: {}
    };
    
    request(options, function (error, response, body) {
      assert.isNull(error);
      assert.isNotNull(response, 'Response');
      assert.equal(response.statusCode, 204, "Expect 204, got " + response.statusCode);
      done();
    });
  });
  
});
