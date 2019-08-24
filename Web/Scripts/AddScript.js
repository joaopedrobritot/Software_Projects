
if(document.getElementById('Rtype').value == 'Hourly')
{
    document.getElementById('displaysalary').innerHTML = 'Salary per hour:';
}
else
{
    document.getElementById('displaysalary').innerHTML = 'Salary per Day:';
}

function Register()
{
    applyState();
    
    var name = document.getElementById('Rname').value;
    var address = document.getElementById('Raddress').value;
    var cpf = document.getElementById('Rcpf').value;
    var _type = document.getElementById('Rtype').value;
    salaryString();
    var salary = document.getElementById('Rsalary').value;
    var pay_method = document.getElementById('Rpayment').value;
    var syndicate = document.getElementById('Rsyndicate').value;
    var syndicateid = '';
    var syndicatetax = '';
    
    if(syndicate === 'Yes')
    {
        if(empty(document.getElementById('Rsyndicateid').value) || empty(document.getElementById('Rsyndicatetax').value))
        {
            alert('\n\n  Insert a Valid Syndicate info!');
            return;
        }
        syndicateid = document.getElementById('Rsyndicateid').value;
        syndicatetax = document.getElementById('Rsyndicatetax').value;
    }
    if(name == '' || address == '' || empty(salary))
    {
        alert('\n\n  Verify if all the values are correct!' + salary);
        return;
    }
    for(var i = 0; i <= Employee_list.length;i++)
    {
            
        if(empty(Employee_list[i]))
        {
            Employee_list[i] = new Employee(name, address, cpf, _type, salary, pay_method, i, syndicate, syndicateid, syndicatetax);
            alert('\n\n  SAVE YOUR ID NUMBER!\n\n' + printc(i) + '');
            break;
        }
    }

    saveState();
        
}