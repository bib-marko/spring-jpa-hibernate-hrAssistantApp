
let templateTitle = document.getElementById("title");
let templateBody = document.getElementById("body");
let saveButton = document.getElementById("btnSave");


function update()
{
    let outty = window.hello.document;
    outty.open();
    let inny=document.getElementById("data");
    outty.write(inny.value);
    outty.close();
}

function realtime()
{
    if (!document.querySelector("#realtime").checked) return;
    update();
}

dataArea=document.getElementById("data");
dataArea.onkeyup=realtime;

update();


fetchDataFromAPI();

let names;
async function fetchDataFromAPI() {
    const response = await fetch('http://localhost:8080/template/list');
    names = await response.json();
    console.log(names);

    const header = `
          <tr>
            <th>ID</th>
            <th>TITLE</th>
            <th>BODY</th>
            <th>CREATED_AT</th>
            <th>UPDATED_AT</th>
            <th>ACTION</th>
          </tr>
        `
    document.querySelector("#tbldata").innerHTML = names.reduce(( innerHTML, { id, title ,created_at, updated_at }) => (
        `
            ${ innerHTML }
            <tr>
                  <td>${ id }</td>
                  <td>${ title }</td>
                  <td><button id="btnDelete" class="button view" value="${ id }" onclick="viewTemplate(${ id })">VIEW TEMPLATE</button></td> 
                  <td>` + formatDate(`${ created_at }`) + `</td>
                  <td>` + formatDate(`${ updated_at }`) + `</td>
                  <td>
                        <button id="btnDelete" class="button delete" value="${ id }" onclick="">DELETE</button>
                        <button id="btnUpdate" class="button update" value="${ id }" onclick="">UPDATE</button>
                  </td>
            </tr>
          `
    ), header );

    function formatDate(dateFromDB) {
        let date = new Date(dateFromDB);
        let year = date.getFullYear();
        let month = date.getMonth()+1;
        let dt = date.getDate();
        let hh = date.getHours();
        let min = date.getMinutes();

        let ampm = hh  >= 12 ? 'PM' : 'AM';
         hh = hh  % 12;
         hh = hh  ? hh  : 12;
        min = min < 10 ? '0'+min : min;
        let strTime = hh  + ':' + min + ' ' + ampm;

        if (dt < 10) {
            dt = '0' + dt;
        }
        if (month < 10) {
            month = '0' + month;
        }

        return year+'-' + month + '-'+dt + " / " + strTime;

    }

}

async function viewTemplate(id){
    for (let key in names) {
        if (names.hasOwnProperty(key)) {
            if(names[key].id === id){
                let str = names[key].body.replaceAll('\\', '');
                console.log(str);
                document.getElementById("data").value = str;
            }

        }
    }
    update();
}

async function addNewBook() {
    fetch('http://localhost:8080/template/new',{
        method: "POST",
        headers: { "Content-type": "application/json", },
        body: JSON.stringify({
            title: templateTitle.value,
            body: templateBody.value
        })
    }).then((res) => res.json()).then(data => console.log(data))
}






