var id;
var info = document.getElementById('info');
document.getElementById('alo').style = 'animation: fadeinout 2.2s 0s forwards;animation-iteration-count:infinite;';

function ApplyChange()
{
    
    applyState();
    id = document.getElementById('Rid').value;
    if(!empty(Employee_list[id]))
    {
        document.getElementById('displayid').innerHTML = id;
        document.getElementById('Rname').value = Employee_list[id].name;
        document.getElementById('Raddress').value = Employee_list[id].address;
        document.getElementById('Rcpf').value = Employee_list[id].cpf;
        document.getElementById('Rtype').value = Employee_list[id].type;
        document.getElementById('Rpayment').value = Employee_list[id].pay_method;
        document.getElementById('Rsyndicate').value = Employee_list[id].syndicate;
        if(Employee_list[id].syndicate == 'Yes')
        {
            document.getElementById('syndicateinfo').style.display = 'block';
            if(empty(Employee_list[id].syndicateid) || empty(Employee_list[id].syndicatetax))
            {
                alert(' Insert a valid Syndicate value!');
                return;
            }
            document.getElementById('Rsyndicateid').value = Employee_list[id].syndicateid;
            document.getElementById('Rsyndicatetax').value = Employee_list[id].syndicatetax;
        }
        else
            document.getElementById('syndicateinfo').style.display = 'none';
        
        info.style.display = 'block';
    }
    else{
        alert("there's no Employee with this ID: " + id);
        info.style.display = 'none';
    }
}

function verify(){
    
    applyState();
    
    var name = document.getElementById('Rname').value;
    var address = document.getElementById('Raddress').value;
    var cpf = document.getElementById('Rcpf').value;
    var type = document.getElementById('Rtype').value;
    var pay_method = document.getElementById('Rpayment').value;
    var syndicate = document.getElementById('Rsyndicate').value;
    var syndicateid = document.getElementById('Rsyndicateid').value;
    var syndicatetax = document.getElementById('Rsyndicatetax').value;

    if(name == '' || address == '' || cpf == '')
    {
        alert('Verify if you credentials are correct!');
        return;
    }
    if(syndicate == 'Yes')
    {
        if(empty(syndicateid) || empty(syndicatetax))
        {
            alert('\n\n  Insert a Valid Syndicate info!');
            return;
        }
    }
    
    
    if(name != Employee_list[id].name){Employee_list[id].name = name;}
    if(address != Employee_list[id].address){Employee_list[id].address = address;}
    if(cpf != Employee_list[id].cpf){Employee_list[id].cpf = cpf;}
    if(type != Employee_list[id].type){
        Employee_list[id].type = type;
        Employee_list[id].selling = 0;
        Employee_list[id].days_worked = 0;
    }
    if(pay_method != Employee_list[id].pay_method){Employee_list[id].pay_method = pay_method;}
    if(syndicate != Employee_list[id].syndicate){
        Employee_list[id].syndicate = syndicate;
        Employee_list[id].service_tax = 0;
    }
    if(syndicateid != Employee_list[id].syndicateid){Employee_list[id].syndicateid = syndicateid;}
    if(syndicatetax != Employee_list[id].syndicatetax){Employee_list[id].syndicatetax = syndicatetax;}

    alert('Change made for the the Employee: ' + Employee_list[id].name + '\n with ID: ' + id);

    saveState();
}