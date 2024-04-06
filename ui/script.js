fetch('http://localhost:8080/api/listServices').then(response=>response.json())
                                               .then(data => {
                                               const stringList = data.services;
                                               const servicesElement = document.getElementById('serviceList');
                                               stringList.forEach(service =>{
                                               const li = document.createElement('li');
                                               li.textContent = service;
                                               servicesElement.appendChild(li);})
                                               })
                                               .catch(error => console.log('Error fetching services: ',error));