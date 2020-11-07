function generate() {
    var url_string = window.location.href;
    var url = new URL(url_string);
    var elementById = url.searchParams.get("password");
    let params = {
        "passphrase": elementById
    };
    console.log(params);
    const http = new XMLHttpRequest();
    http.open('POST', 'http://localhost:8080/api/v0/generateWallet', true);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(JSON.stringify(params)); // Make sure to stringify
    http.onload = function () {
        if (this.status === 200) {
            let res = JSON.parse(this.responseText).data;
            console.log(res.address);
            document.getElementById("privateKey").innerText = res.encryptPrivateKey;
            document.getElementById("address").innerText = res.address;
            document.getElementById("publicKey").innerText = res.publicKey;

        } else {
            console.log("EEEEE");
            alert(JSON.parse(this.responseText).message);
        }
    }
}

generate();