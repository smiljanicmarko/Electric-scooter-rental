INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO adresa (ulica, broj) VALUES ("Slobodana Bajica", 14);
INSERT INTO adresa (ulica, broj) VALUES ("Bulevar Oslobodjenja", 10);
INSERT INTO adresa (ulica, broj) VALUES ("Laze Nancica", 3);

              
              
INSERT INTO trotinet (sifra, baterija, brzina, iznajmljen, adresa_id)
VALUES ("001", 99, 30, false, 1);
INSERT INTO trotinet (sifra, baterija, brzina, iznajmljen, adresa_id)
VALUES ("002", 10, 30, false, 2);
INSERT INTO trotinet (sifra, baterija, brzina, iznajmljen, adresa_id)
VALUES ("003", 75, 30, false, 3);
INSERT INTO trotinet (sifra, baterija, brzina, iznajmljen, adresa_id)
VALUES ("004", 88, 30, false, 2);
INSERT INTO trotinet (sifra, baterija, brzina, iznajmljen, adresa_id)
VALUES ("005", 30, 30, false, 1);

              
--'2020-06-21 20:00'
--'2020-07-22 21:00'
--'2020-06-22 20:00'
--'2020-08-10 18:00'
--'2020-08-12 19:00'

