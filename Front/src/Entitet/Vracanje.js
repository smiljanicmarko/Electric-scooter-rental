import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";
import TestAxios from '../apis/TestAxios';
import { FormGroup, FormLabel, Row, Col, Form, Button } from 'react-bootstrap';



const Vracanje = () => {


    var kostur = {
        email: '',
        baterija: '',
        trotinetId: '',
        adresaId: ''
    };

//============================================= S T A T E ============================================================    
    const [rezultat, setRezultat] = useState([]) // STATE ZA SELECT OPCIJU
    const [objekat, setObjekat] = useState(kostur);

    
//============================================== P A R A M S,  N A V I G A T E ========================================
    var navigate = useNavigate();
    const params = useParams();
    const trotinetId = params.id  

  
// ==================================== GLAVNA AXIOS FUNKCIJA ZA KREIRANJE ============================================
    const create = () => {
        var params = {
            email: objekat.email,
        baterija: objekat.baterija,
        trotinetId: trotinetId,
        adresaId: objekat.adresaId,
        };

        TestAxios.put('/rezervacije/vrati', params)
        .then(res => {
            console.log(res);
           
            alert('Uspesno ste vratili trotinet!');
            navigate('/zadaci'); 
        })
        .catch(error => {           
            console.log(error);

            error.response.data.message?
            alert( error.response.data.message):
            alert ("Doslo je do greske! Popunite sva polja i probajte ponovo!")
         });
    }

    // ============= FUNKCIJA ==============
    
    const valueInputChanged = (e) => {
        const { name, value } = e.target;        
        setObjekat((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };


    // ========================================== DOBAVLJANJE PODATAKA ZA SELECT=========================================
    const dobaviPodatke = useCallback(() => {
        TestAxios.get("/adrese")
            .then(res => {
               
                console.log(res);
                setRezultat(res.data)               
            })
            .catch(error => {
              
                console.log(error);
                alert('Doslo je do greske, molimo pokusajte ponovo!');
            });
    }, []);


    useEffect(() => {
        dobaviPodatke();
    }, []);

//= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = GLAVNI RETURN = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
//= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = GLAVNI RETURN = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
return(
<div>
    <h1>Vracanje trotineta</h1>

    <Row>
                <Col></Col>
                <Col></Col>
                <Col xs="12" sm="10" md="4">
                   
                    <Form>
                        
                      <FormGroup>
                        <FormLabel htmlFor='email'>Email</FormLabel>
                        <Form.Control type='email' id='email' name='email' onChange={valueInputChanged}></Form.Control>
                      </FormGroup>

                      <FormGroup>
                        <FormLabel htmlFor='baterija'>Stanje baterije</FormLabel>
                        <Form.Control type='number' id='baterija' name='baterija' onChange={valueInputChanged}></Form.Control>
                      </FormGroup>

                     
 
                      <FormGroup>
                        <FormLabel htmlFor='adresaId'>Adresa</FormLabel>
                        <Form.Control as='select' id='adresaId' name='adresaId' onChange={valueInputChanged}>
                        <option value=''>Izaberi opciju</option>
                        {
                            rezultat.map((obj, index) =>{
                                return (
                                    <option key={obj.id} value={obj.id}> {obj.ulica} {obj.broj} </option>
                                )
                            })
                        }
                        </Form.Control>
                      </FormGroup>
 

                        <Button  onClick={() => create()}  >Add</Button>
                    </Form>
                </Col>
                <Col></Col>
                <Col></Col>
                
            </Row>
</div>
)}

export default Vracanje