//set date

var week_name = ['Sunday','Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday','Saturday'];
var day_of_week = 5;
var day = 16;
var month = 8;
var year = 2019;

onload = applyState();

var HeaderHTML = "<head class='FadeIn'><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><meta http-equiv='X-UA-Compatible' content='ie=edge'><title>Web Project</title><div width=500 ><br><br><header style='text-align: center'><h1 onclick='ReturntoIndex()' class='headertxt'>Payment System</h1></header> <div ><button onclick='AddEmployee()' class='button'><img src='Icones/sword.png' alt='' class='iconbutton'>Add Employee</button><button onclick='RemoveEmployee()' class='button'><img src='Icones/pentagon.png' alt='' class='iconbutton'>  Remove Employee</button><button onclick='ListAllEmployees()' class='button'><img src='Icones/skill.png' alt='' class='iconbutton'>  List All Employees  </button></div></div>  <link rel='stylesheet' href='maincss.css'></head>";

var Employee_list = [];

function printDate(){
    if(day < 10 && month < 10)
        return '0' + day + '/' + '0' + month + '/' + year;
    else if(day < 10 && month >= 10)
        return '0' + day + '/' + month + '/' + year;
    else if(day >= 10 && month < 10)
        return day + '/' + '0' + month + '/' + year;
    else
        return day + '/' + month + '/' + year;
}

function addDay(){
    day++;
    day_of_week++;
    if(day_of_week === 7)
        day_of_week = 0;
    if(day === 32)
    {
        day = 0;
        month++;
        if(month == 13)
            year++; 
    }
}

//

var Employee = function(name, address, _type, pay_method, id, syndicate, syndicateid,syndicatetax){
    var self = this;
    self.name = name;
    self.address = address;
    self.pay_method = pay_method;
    self.id = id;
    self.type = _type;
    if(syndicate)
    {
        self.syndicate = syndicate;
        self.syndicateid = syndicateid;
        self.syndicatetax = syndicatetax;
    }
    else{
        self.syndicate = 'No';
    }

    // isso é um metodo

    

    //
}

printc = function(i)
{
    if(!empty(Employee_list[i]))
    {
        var self = Employee_list[i];
        var aux = '';
        if(self.syndicate == 'Yes')
        {
            aux = '\nSyndicate ID: ' + self.syndicateid + '\nSyndicate Tax: ' + self.syndicatetax;
        }
        return 'ID: ' + i + '\nNome: ' + self.name + '\nEndereço: ' + self.address + '\nType: ' + self.type + '\nPayment method: ' + self.pay_method + '\nSyndicated: ' + self.syndicate + aux;
    }
    else{
        return '';
    }
    
}

printcHTML = function(i)
{
    if(!empty(Employee_list[i]))
    {
        var self = Employee_list[i];
        var aux = '';
        if(self.syndicate == 'Yes')
        {
            aux = '<br>Syndicate ID: ' + self.syndicateid + '<br>Syndicate Tax: ' + self.syndicatetax;
        }
        return 'ID: ' + i + '<br>Nome: ' + self.name + '<br>Endereço: ' + self.address + '<br>Type: ' + self.type + '<br>Payment method: ' + self.pay_method + '<br>Syndicated: ' + self.syndicate + aux;
    }
    else{
        return '';
    }
    
}

function saveState()
{
    var saveState = JSON.stringify(Employee_list);
    localStorage.setItem('state', saveState);
}

function applyState()
{
    var x = JSON.parse(localStorage.getItem('state'))
    if(x == null)
    {
        Employee_list = [];
    }
    else{
        Employee_list = x;
    }
}

function AddEmployee()
{
    location = 'AddEmployee.html';
    //document.body.innerHTML = URL('AddEmployee.html');
}
function RemoveEmployee()
{
    location = 'RemoveEmployee.html';
}
function OpenList()
{
    //document.body.innerHTML = HeaderHTML + '<div id="employeelist"></div><script src="Scripts/MainScript.js"></script>';
    location = 'ListAllEmployees.html';
    
}
function ListAllEmployees()
{
    applyState();
    var x = document.getElementById('employeelist');
    for(var i = 0;i<Employee_list.length;i++)
    {
            x.innerHTML += printcHTML(i) + '<br><br>';
    }
}
function ReturntoIndex()
{
    location = 'index.html';
}
function showSyndicateOP()
{
    var x = document.getElementById('syndicateinfo');
    var y = document.getElementById('Rsyndicate');
    if(y.value === 'No')
    {
        x.style.visibility = 'hidden';
    }
    else{
        x.style.visibility = 'visible';
    }
}

function empty(data)
{
  if(typeof(data) == 'number' || typeof(data) == 'boolean')
  { 
    return false; 
  }
  if(typeof(data) == 'undefined' || data === null)
  {
    return true; 
  }
  if(typeof(data.length) != 'undefined')
  {
    return data.length == 0;
  }
  var count = 0;
  for(var i in data)
  {
    if(data.hasOwnProperty(i))
    {
      count ++;
    }
  }
  return count == 0;
}
