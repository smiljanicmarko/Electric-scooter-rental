import React, { useState, useEffect, useCallback } from 'react';
import { Button, Col, Form, FormGroup, FormLabel, Row, Table } from 'react-bootstrap';
import { useNavigate } from "react-router-dom";
import TestAxios from '../apis/TestAxios';
import { jwtDecode } from 'jwt-decode';

const Zadaci = () => {

     //=================================== AUTORIZACIJA =========================================
     const token = localStorage.getItem("jwt");
     const decoded = token ? jwtDecode(token) : null;
     const isAdmin = decoded?.role?.authority === "ROLE_ADMIN";

    //========================== OBJEKAT PRETRAGE ==================================

    var pretragaObjekat = {
        adresaId: '',
         baterijaOd: '',
         baterijaDo: ''
       }
    // ========================== STATE ============================================
    const [tabela, setTabela] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [totalPages, setTotalPages] = useState(0)
    const [prikaziFormu, setPrikaziFormu] = useState(false);
    const [pretraga, setPretraga] = useState(pretragaObjekat)
    const [rezultat, setRezultat] = useState([]) // STATE ZA SELECT OPCIJU


    // /////////////////////////////////////////////////////// J A V A  S C R I P T  F U N K C I J E \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    //======================== USE EFFECT ============================================
    useEffect(() => {
        getZadaci();
    }, [pageNo, pretraga]);
    
    
   
    // ======================== DOBAVLJANJE PODATAKA ================================


    const getZadaci = useCallback(() => {
        TestAxios.get(`/trotineti?pageNo=${pageNo}`,{
            params:{
                ...pretraga
            }
        })
            .then(res => {
                console.log(res);
                setTabela(res.data)
                setTotalPages(res.headers["total-pages"])
            })
            .catch(error => {
                console.log(error);
                alert('Error occured please try again!');
            });
    }, [pageNo, pretraga]);


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
     //======================== NAVIGATE ============================================
     var navigate = useNavigate()

     const goToAdd = () => {
         navigate("/dodavanje");
     }
 

    // ======================== BRISANJE ===========================================
    const izbrisi = (id) => {
        TestAxios.delete('/trotineti/' + id)
            .then(res => {
                // handle success
                console.log(res);
                alert('Brisanje je uspesno izvrseno!');
                setTabela(tabela.filter(el => el.id !==id))
               // window.location.reload();

            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Doslo je do greske, molimo pokusajte ponovo!');
            });
    }

    //============================================ HANDLERI ZA FORME I VALUE INPUT CHANGED ===============================

    const formaHandler = () => {
        setPrikaziFormu(!prikaziFormu);
    };

    const valueInputChanged = (e) => {
      const { name, value } = e.target;
      setPretraga((prevState) => ({
           ...prevState,
           [name]: value,
       }));
   };

   const resetPretraga = () =>{
    setPretraga({
        adresaId: '',
         baterijaOd: '',
         baterijaDo: ''
    });
}

    {/* ================================================ RENDER TABELE ========================================= */ }
    //=============================================================================================================
    const renderTabela = () => {
        return tabela.map((klasa, index) => {
            return (
                <tr key={klasa.id}>
                    <td>{klasa.sifra}</td>
                    <td>{klasa.brzina}km/h</td>
                    <td>{klasa.baterija}%</td>
                    <td>{klasa.adresa} {klasa.broj}</td>
                  
                    {/* === DUGMICI ===*/}
                    {isAdmin?(klasa.iznajmljen? <td>Iznajmljen</td> : <td><Button className='btn btn-danger' onClick={() => izbrisi(klasa.id)}>Izbrisi</Button></td>) : <td></td>}
                    {!isAdmin?((klasa.baterija>10 && !klasa.iznajmljen)?<td> <Button className='btn btn-success' onClick={()=>goToRezervacija(klasa.id)}>Iznajmi</Button> </td>: <td>Nije dostupan</td>) : <td></td> }
                    {!isAdmin? (klasa.iznajmljen? <td><Button className='btn btn-warning' onClick={() => goToVrati(klasa.id)}>Vrati trotinet</Button></td> :<td></td>) : <td></td>}
                    {isAdmin? (!klasa.iznajmljen?<td><Button onClick={()=>napuni (klasa.id)}>Napuni</Button></td> :<td></td>) : <td></td>}
                </tr>
                
            )
        })
    }

//========================================== RENDER FORME ZA PRETRAGU====================================================
//=======================================================================================================================
const renderFormu = () => {
    return (
        <div>
        <Form>
          <Row className="align-items-end"> 
            
            <Col md={2}>
              <FormGroup>
                <FormLabel htmlFor="baterijaOd">Nivo baterije od</FormLabel>
                <Form.Control type='number' name="baterijaOd" id="baterijaOd" value={pretraga.baterijaOd} onChange={valueInputChanged}></Form.Control>
              </FormGroup>
            </Col>
            <Col md={2}>
              <FormGroup>
                <FormLabel htmlFor="baterijaDo">Nivo baterije do</FormLabel>
                <Form.Control type='number' name="baterijaDo" id="baterijaDo" value={pretraga.baterijaDo} onChange={valueInputChanged}></Form.Control>
              </FormGroup>
            </Col>

{/*============== S E L E C T  /   PADAJUCI MENI ======= onChange NIKAKO U LABEL!!! =========== */} 
            <Col md={3}>
            <FormGroup>
                    <FormLabel htmlFor='adresaId'>Adresa</FormLabel>
                    <Form.Control as='select' id='adresaId' name='adresaId' value={pretraga.adresaId} onChange={valueInputChanged}>
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
            </Col>
            <Col><Button className='btn btn-danger'  onClick={resetPretraga}>Ponisti pretragu</Button></Col>
{/*============== S E L E C T  /   PADAJUCI MENI ======= onChange NIKAKO U LABEL!!! =========== */} 
                                                              
          </Row>
        </Form>
      </div>
      
    )
}


const goToRezervacija = (id) =>{
    navigate('/rezervacija/' +id)
}

const goToVrati = (id) =>{
    navigate ('/vracanje/' +id)
}

const napuni = (id) =>{
    TestAxios.put('/trotineti/'+ id + '/napuni' )
        .then(res => {
            // handle success
            console.log(res);
            alert('Trotinet je uspesno napunjen!');
            getZadaci();
            navigate('/zadaci');
        })
        .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
         });
}



    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = GLAVNI RETURN = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = GLAVNI RETURN = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
    return (
        <div>
            <h1>TROTINETI</h1>
            {/* ================================== PRETRAGA meni================= */}

            <div>            
            <Form.Check type="checkbox"  label="Prikazi meni za pretragu" onChange={formaHandler} />
            {prikaziFormu && renderFormu()}
            <br/>
        </div>





            {/* ================================== ADD + PAGINACIJA IZNAD TABELE ================= */}
            {isAdmin? <Button className="btn btn-primary" onClick={goToAdd} >Dodaj</Button> : <></>}
            

            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
                <Row>
                    <Col>
                        <Button disabled={pageNo <= 0} onClick={() => setPageNo(pageNo - 1)}>Previous</Button>
                    </Col>
                    <Col>
                        <Button disabled={pageNo >= totalPages - 1} onClick={() => setPageNo(pageNo + 1)}>Next</Button>
                        <span> {pageNo + 1}/{totalPages}</span>
                    </Col>
                </Row>
            </div>
            <Row><Col>
                <Table id="movies-table">
                    <thead>
                        <tr>
                            {/* ================================== ZAGLAVLJE TABELE ================= */}
                            <th>Sifra</th>
                            <th>Maksimalna brzina (km/h)</th>
                            <th>Nivo baterije</th>
                            <th>Trenutna adresa</th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            
                        </tr>
                    </thead>
                    {/* ================================== TELO TABELE  ================= */}
                    <tbody>
                        {renderTabela()}
                    </tbody>
                </Table>
            </Col></Row>

        </div>
    )

}

export default Zadaci