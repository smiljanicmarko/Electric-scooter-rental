import React, { useCallback, useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import TestAxios from '../apis/TestAxios';
import { FormGroup, FormLabel, Row, Col, Form, Button } from 'react-bootstrap';



const Dodavanje = () => {

//DEKLARACIJA OBJEKTA, SA IMENIMA IZ DTO! OBAVEZNO ISTA IMENA U NAME ATRIBUT U HTML!
    var kostur = {
        sifra: '',
        brzina: '',
        adresaId: ''
    };

//============================================= S T A T E ============================================================    
    const [rezultat, setRezultat] = useState([]) // STATE ZA SELECT OPCIJU
    const [objekat, setObjekat] = useState(kostur);

    var navigate = useNavigate();
// ==================================== GLAVNA AXIOS FUNKCIJA ZA KREIRANJE ============================================
    const create = () => {
        // var params = {
        //     'naziv': objekat.ime,
        //     'trajanje': objekat.duration
        // };

        TestAxios.post('/trotineti', objekat)
        .then(res => {
            console.log(res);
           
            alert('Dodavanje je uspesno izvrseno!');
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


    // ======================== DOBAVLJANJE PODATAKA ZA SELECT================================
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
    <h1>DODAVANJE</h1>

    <Row>
                <Col></Col>
                <Col></Col>
                <Col xs="12" sm="10" md="4">
                   
                    <Form>
                        
                      <FormGroup>
                        <FormLabel htmlFor='sifra'>Sifra</FormLabel>
                        <Form.Control type='text' id='sifra' name='sifra' onChange={valueInputChanged}></Form.Control>
                      </FormGroup>

                      <FormGroup>
                        <FormLabel htmlFor='brzina'>Maksimalna brzina</FormLabel>
                        <Form.Control type='number' id='brzina' name='brzina' onChange={valueInputChanged}></Form.Control>
                      </FormGroup>

                     
 {/*===================================== S E L E C T  /   PADAJUCI MENI ======= onChange NIKAKO U LABEL!!! ========================== */} 
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
  {/*===================================== S E L E C T  /   PADAJUCI MENI ======= onChange NIKAKO U LABEL!!! ========================== */} 

                        <Button  onClick={() => create()}  >Add</Button>
                    </Form>
                </Col>
                <Col></Col>
                <Col></Col>
                
            </Row>
</div>
)}

export default Dodavanje