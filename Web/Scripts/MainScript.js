
onchange = applyState();
this.document.body.style.opacity = 1;


var HeaderHTML = "<head class='FadeIn'><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'><meta http-equiv='X-UA-Compatible' content='ie=edge'><title>Web Project</title><div width=500 ><br><br><header style='text-align: center'><h1 onclick='ReturntoIndex()' class='headertxt'>Payment System</h1></header> <div ><button onclick='AddEmployee()' class='button'><img src='Icones/sword.png' alt='' class='iconbutton'>Add Employee</button><button onclick='RemoveEmployee()' class='button'><img src='Icones/pentagon.png' alt='' class='iconbutton'>  Remove Employee</button><button onclick='ListAllEmployees()' class='button'><img src='Icones/skill.png' alt='' class='iconbutton'>  List All Employees  </button></div></div>  <link rel='stylesheet' href='maincss.css'></head>";

var Employee_list = [];

function formatar(mascara, documento){

    var i = documento.value.length;
    var saida = mascara.substring(0,1);
    var texto = mascara.substring(i);
    documento.value = documento.value.replace(/[^0-9.-]/g, '');
    if (texto.substring(0,1) != saida){
              documento.value += texto.substring(0,1);
    }
    
  }

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

var Employee = function(name, address, cpf, _type, salary, pay_method, id, syndicate, syndicateid,syndicatetax){
    var self = this;
    self.cpf = cpf;
    self.name = name;
    self.address = address;
    self.salary = salary;
    self.days_worked = 0;
    self.selling = 0;
    self.service_tax = 0;
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
}

function calculate(employee)
{
    var total = 0;
    if(employee.type == 'Hourly')
    {
        total = (employee.salary * 24 * employee.days_worked);
    }
    else
        total = (employee.salary * employee.days_worked) + (employee.selling * 0.2);
    if(employee.syndicate == 'Yes')
        total = total -  employee.service_tax - employee.syndicatetax;
    if(total < 0)
        total = 0;
    return total;
}

/*


TODO : fazer o resto das funcoes do payment related e dps tentar implementar o system message. 



*/

printc = function(i)
{
    if(!empty(Employee_list[i]))
    {
        var self = Employee_list[i];
        var aux = '';
        var salaryType = '';
        if(!(self.type == 'Hourly'))
            salaryType = '\nSalary per day: $' + self.salary;
        else
            salaryType = '\nSalary per hour: $' + self.salary;
        if(self.syndicate == 'Yes')
        {
            aux = '\nSyndicate ID: ' + self.syndicateid + '\nSyndicate Tax: ' + self.syndicatetax;
        }
        return 'ID: ' + i + '\nNome: ' + self.name + '\nEndereço: ' + self.address + '\nCPF: ' + self.cpf + '\nType: ' + self.type + salaryType + '\nPayment method: ' + self.pay_method + '\nSyndicated: ' + self.syndicate + aux;
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
        var salaryType = '';
        var selling = '';
        if(!(self.type == 'Hourly'))
        {
            salaryType = '<br>Salary per day: $' + self.salary;
            if(self.type == 'Commissioned')
                selling = '<br>Sellings: $' + self.selling;
        }
        else
            salaryType = '<br>Salary per hour: $' + self.salary;
        var aux = '';
        if(self.syndicate == 'Yes')
            aux = '<br>Syndicate ID: ' + self.syndicateid + '<br>Syndicate Tax: $' + self.syndicatetax + '<br>Service Taxes: $' + self.service_tax;
        return 'ID: ' + i + '<br>Nome: ' + self.name + '<br>Endereço: ' + self.address + '\<br>CPF: ' + self.cpf + '<br>Type: ' + self.type + salaryType + '<br>Salary To Receive (20%): $' + calculate(self) + selling + '<br>Days Worked: ' + self.days_worked + '<br>Payment method: ' + self.pay_method + '<br>Syndicated: ' + self.syndicate + aux;
    }
    else{
        return '';
    }
    
}

function salaryString()
{
    if(document.getElementById('Rtype').value == 'Hourly')
        document.getElementById('displaysalary').innerHTML = 'Salary per hour:';
    else
        document.getElementById('displaysalary').innerHTML = 'Salary per Day:';
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
    document.body.style.backgroundColor = 'white';
    $('html').fadeOut(1500, function(){
        location = 'AddEmployee.html';
    });
    
    //document.body.innerHTML = URL('AddEmployee.html');
}
function RemoveEmployee()
{
    $('html').fadeOut(1500, function(){
        location = 'RemoveEmployee.html';
    });
    
}
function OpenList()
{
    $('html').fadeOut(1500, function(){
        location = 'ListAllEmployees.html';
    });
}
function ListAllEmployees()
{
    applyState();
    var x = document.getElementById('employeelist');
    x.innerHTML = '<hr>';
    for(var i = 0;i<Employee_list.length;i++)
    {
        if(!empty(Employee_list[i]))
            x.innerHTML += printcHTML(i) + '<br><br>' + '<hr><br>';
    }
}
function RChangeEmployee()
{
    $('html').fadeOut(1500, function(){
        location = 'ChangeEmployee.html';
    });
}
function Payment()
{
    $('html').fadeOut(1500, function(){
        location = 'Payment.html';
    });
}

function ReturntoIndex()
{
    $('html').fadeOut(1500, function(){
        location = 'index.html';
    });
}
function showSyndicateOP()
{
    
    
    var x = document.getElementById('syndicateinfo');
    var y = document.getElementById('Rsyndicate');
    if(y.value === 'No')
    {
        x.style.display = 'none';
    }
    else{
        x.style.display = 'block';
    }
}

function empty(data)
{
  if(typeof(data) == 'boolean')
  { 
    return false; 
  }
  if(typeof(data) == 'number')
  {
      if(isNaN(data))
        return true;
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
