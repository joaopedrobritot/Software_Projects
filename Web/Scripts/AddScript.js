function Register()
{
    applyState();
    var name = document.getElementById('Rname').value;
    var address = document.getElementById('Raddress').value;
    var _type = document.getElementById('Rtype').value;
    var pay_method = document.getElementById('Rpayment').value;
    var syndicate = document.getElementById('Rsyndicate').value;
    var syndicateid = document.getElementById('Rsyndicateid').value;
    var syndicatetax = document.getElementById('Rsyndicatetax').value;
    if(name == '' || address == '')
    {
        alert('\n\n  Verify if all the values are correct!');
    }
    else{
        for(var i = 0; i <= Employee_list.length;i++)
        {
            if(empty(Employee_list[i]))
            {
                Employee_list[i] = new Employee(name, address, _type, pay_method, i, syndicate, syndicateid, syndicatetax);
                alert('\n\n  SAVE YOUR ID NUMBER!\n\n' + printc(i) + '');
                break;
            }
        }

        saveState();
        //Employee_list.push(new Employee(name, address, _type, pay_method, id, syndicate, syndicateid, syndicatetax));
        
    }
}