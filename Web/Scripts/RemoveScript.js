function Remove()
{
    applyState();
    var id = parseInt(document.getElementById('Rid').value)
    if(empty(Employee_list[id]))
    {
        alert("There's no Employee with this ID! " + id);
    }
    else{
        if(confirm(printc(id) + '\n\n\n Are you sure to Delete this Employee?'))
        {
            Employee_list[id] = null;
            alert(' Employee with ID:' + id + ' Removed from system!');
        }
        saveState();
    }
}