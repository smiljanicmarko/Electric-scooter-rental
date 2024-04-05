import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate, useParams } from "react-router-dom";
import TestAxios from '../apis/TestAxios';
import { FormGroup, FormLabel, Row, Col, Form, Button } from 'react-bootstrap';



const Dodatno = () => {

//DEKLARACIJA OBJEKTA, SA IMENIMA IZ DTO! OBAVEZNO ISTA IMENA U NAME ATRIBUT U HTML!
    var kostur = {
        email: '',
       trotinetId: ''
    };

//============================================= S T A T E ============================================================    
    
    const [objekat, setObjekat] = useState(kostur);

    
//============================================== P A R A M S,  N A V I G A T E ========================================
    var navigate = useNavigate();
    const params = useParams();
    const idTrotineta = params.id  

  
// ==================================== GLAVNA AXIOS FUNKCIJA ZA KREIRANJE ============================================
    const create = () => {
        var params = {
            email: objekat.email,
            trotinetId: idTrotineta
        };

        TestAxios.post('/rezervacije', params)
        .then(res => {
            console.log(res);
           
            alert('Rezervacija je uspesno izvrsena!');
            navigate('/zadaci'); 
        })
        .catch(error => {           
            console.log(error);
            alert('Doslo je do greske, molimo pokusajte ponovo!');
         });
    }

    // ============= GENERICKA FUNKCIJA - OBAVEZNO IMATI OBJEKAT SA IMENIMA IZ DTO + "NAME" atribut u html!==============
    // ==============ALTERNATIVA JE  (e) => setNesto (e.target.value)=====================
    const valueInputChanged = (e) => {
        const { name, value } = e.target;        
        setObjekat((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };


   
return(
<div>
    <h1>REZERVACIJA</h1>

    <Row>
                <Col></Col>
                <Col></Col>
                <Col xs="12" sm="10" md="4">
                   
                    <Form>
                        
                      <FormGroup>
                        <FormLabel htmlFor='email'>Email</FormLabel>
                        <Form.Control type='email' id='email' name='email' onChange={valueInputChanged}></Form.Control>
                      </FormGroup>

                    

                        <Button  onClick={() => create()}  >Add</Button>
                    </Form>
                </Col>
                <Col></Col>
                <Col></Col>
                
            </Row>
</div>
)}

export default Dodatno