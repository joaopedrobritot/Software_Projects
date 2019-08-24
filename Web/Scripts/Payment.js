var img1 = document.getElementById('img1');
var img2 = document.getElementById('img2');
var img3 = document.getElementById('img3');
var img4 = document.getElementById('img4');

var img01 = document.getElementById('img01');
var img02 = document.getElementById('img02');
var img03 = document.getElementById('img03');
var img04 = document.getElementById('img04');

document.getElementsByName('b1')[0].style.backgroundColor = '#CDC9AE';
document.getElementsByName('b1')[0].style.color = '#4E4B46';
document.getElementsByName('b1')[0].style.animation = 'initial';
document.getElementsByName('b2')[0].style.backgroundColor = '#CDC9AE';
document.getElementsByName('b2')[0].style.color = '#4E4B46';
document.getElementsByName('b2')[0].style.animation = 'initial';
document.getElementsByName('b3')[0].style.backgroundColor = '#CDC9AE';
document.getElementsByName('b3')[0].style.color = '#4E4B46';
document.getElementsByName('b3')[0].style.animation = 'initial';
document.getElementsByName('b4')[0].style.backgroundColor = '#CDC9AE';
document.getElementsByName('b4')[0].style.color = '#4E4B46';
document.getElementsByName('b4')[0].style.animation = 'initial';

////////////////// DISPLAYS

function TimeCard(option)
{
    clearDivs();
    applyColor('b1');
    img1.style.display = 'inline-block';
    img01.style.backgroundColor = '#DAD4BC';
    img1.src = 'Icones/niercursorselect.png';
    option.style.display = 'block';
}
function Selling(option)
{
    clearDivs();
    applyColor('b2');
    img2.style.display = 'inline-block';
    img02.style.backgroundColor = '#DAD4BC';
    img2.src = 'Icones/niercursorselect.png';
    option.style.display = 'block';
}
function Service(option)
{
    clearDivs();
    applyColor('b3');
    img3.style.display = 'inline-block';
    img03.style.backgroundColor = '#DAD4BC';
    img3.src = 'Icones/niercursorselect.png';
    option.style.display = 'block';
}
function Pay(option)
{
    clearDivs();
    applyColor('b4');
    img4.style.display = 'inline-block';
    img04.style.backgroundColor = '#DAD4BC';
    img4.src = 'Icones/niercursorselect.png';
    option.style.display = 'block';
}
///////////////// FUNCTIONS

function check()
{
    var id = document.getElementById('Rid').value;
    Employee_list[id].days_worked++;
    saveState();
    alert(' + 1 worked day to Employee: ' + Employee_list[id].name + '\n with ID: ' + id);
    document.getElementsByName('b1')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b1')[0].style.color = '#4E4B46';
    document.getElementsByName('b1')[0].style.animation = 'initial';
    clearDivs();
}
function submitSell()
{
    var id = document.getElementById('Rid').value;
    var selling = Math.round(parseFloat(document.getElementById('selling').value) * 100) / 100;
    if(!empty(selling))
    {
        Employee_list[id].selling += selling;
    }
    else
    {
        alert('Invalid Selling value!');
        return;
    }
    saveState();
    alert(' The selling: $' + selling + '\n Has been added to user :' + Employee_list[id].name + '\n With ID: ' + id);
    document.getElementsByName('b2')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b2')[0].style.color = '#4E4B46';
    document.getElementsByName('b2')[0].style.animation = 'initial';
    clearDivs();
}
function serviceReg()
{
    var id = document.getElementById('Rid').value;
    var service = Math.round(parseFloat(document.getElementById('service').value) * 100) / 100;
    if(!empty(service))
    {
        Employee_list[id].service_tax += service;
    }
    else
    {
        alert('Invalid Service value!');
        return;
    }
    saveState();
    alert(' The Service tax: $' + service + '\n Has been added to user :' + Employee_list[id].name + '\n With ID: ' + id);
    document.getElementsByName('b3')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b3')[0].style.color = '#4E4B46';
    document.getElementsByName('b3')[0].style.animation = 'initial';
    clearDivs();
}
function roll()
{
    var id = document.getElementById('Rid').value;
    var total = 0;
    ////////////

    total = calculate(Employee_list[id]);
    alert('\n\n\nName: ' + Employee_list[id].name + '\nID: ' + id + '\nReceived: $' + total);
    Employee_list[id].days_worked = 0;
    Employee_list[id].selling = 0;
    Employee_list[id].service_tax = 0;
    
    ////////////
    saveState();
    document.getElementsByName('b4')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b4')[0].style.color = '#4E4B46';
    document.getElementsByName('b4')[0].style.animation = 'initial';
    clearDivs();
}

/////////////////// AUX

function checkIdFunction()
{
    applyState();
    clearDivs();
    var id = document.getElementById('Rid').value;
    document.getElementById('showid').innerHTML = 'Employee ID: ' + id;
    if(!empty(Employee_list[id]))
    {
        document.getElementById('view').style.display = 'block';
        document.getElementById('p').style.display = 'block';
        document.getElementById('employee').innerHTML = printcHTML(id);
        if(Employee_list[id].type == 'Commissioned')
        {
            document.getElementById('suberror').style.display = 'none';
            document.getElementById('sub').style.display = 'block';
        }
        else{
            document.getElementById('sub').style.display = 'none';
            document.getElementById('suberror').style.display = 'block';
        }
        if(Employee_list[id].syndicate == 'Yes')
        {
            document.getElementById('sererror').style.display = 'none';
            document.getElementById('ser').style.display = 'block';
        }
        else{
            document.getElementById('ser').style.display = 'none';
            document.getElementById('sererror').style.display = 'block';
        }
    }
    else{
        alert('Employee not found!');
        document.getElementById('view').style.display = 'none';
    }
}

function clearDivs()
{
    var id = document.getElementById('Rid').value;
    document.getElementsByName('b1')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b1')[0].style.color = '#4E4B46';
    document.getElementsByName('b1')[0].style.animation = 'initial';
    document.getElementsByName('b2')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b2')[0].style.color = '#4E4B46';
    document.getElementsByName('b2')[0].style.animation = 'initial';
    document.getElementsByName('b3')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b3')[0].style.color = '#4E4B46';
    document.getElementsByName('b3')[0].style.animation = 'initial';
    document.getElementsByName('b4')[0].style.backgroundColor = '#CDC9AE';
    document.getElementsByName('b4')[0].style.color = '#4E4B46';
    document.getElementsByName('b4')[0].style.animation = 'initial';

    document.getElementById('time').style.display = 'none';
    document.getElementById('sell').style.display = 'none';
    document.getElementById('serv').style.display = 'none';
    document.getElementById('pay').style.display = 'none';
    document.getElementById('employee').innerHTML = printcHTML(id);

    img1.style.display = 'none';
    img2.style.display = 'none';
    img3.style.display = 'none';
    img4.style.display = 'none';

    img01.style.backgroundColor = '#4D4A41';
    img02.style.backgroundColor = '#4D4A41';
    img03.style.backgroundColor = '#4D4A41';
    img04.style.backgroundColor = '#4D4A41';
}

function applyColor(element)
{
    document.getElementsByName(element)[0].style.backgroundColor = '#4E4B46';
    document.getElementsByName(element)[0].style.color = '#CDC9AE';
    document.getElementsByName(element)[0].style.animation = 'fadeinout 2.2s 0s forwards';
    document.getElementsByName(element)[0].style.animationIterationCount = 'infinite';
}