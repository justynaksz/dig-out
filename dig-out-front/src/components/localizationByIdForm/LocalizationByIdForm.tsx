import React from 'react';
import { FormEvent, useState } from 'react';
import api from '../../api/axiosConfig.js';
import { Localization, LocalizationType } from '../localization/Localization.tsx';
import  '../../pages/Pages.scss';

export const LocalizationByIdForm = () => {
  const [id, setId] = useState ('');
  const [localization, setLocalization] = useState<LocalizationType>();
  const [isResultVisible, setIsResultVisible] = useState(false);
  

  const getLocalization = async (id : string) => {
      try {
        setLocalization((await api.get(`/localizations/${id}`)).data);
      } catch (error) {
          setIsResultVisible(false);
          console.log(error);
      }
  };   

  const handleInput = (e: React.FormEvent<HTMLInputElement>) => {
    setId(e.currentTarget.value);
  }

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    setIsResultVisible(true);
    getLocalization(id);
  };

  return (
    <div>
    <h4 className='title'> FIND LOCALIZATION BY ID </h4>
    <form className='body' onSubmit={handleSubmit}>
      <label htmlFor='id'>
        <input 
          id='id'
          value={id}
          onChange={handleInput}
          placeholder='ID'/>
      </label>
      <button className='button' type='submit'>Find</button>
    </form>
    
    {isResultVisible && 
      <Localization 
      id={localization?.id!}
      cemetery={localization?.cemetery!}
      quarter={localization?.quarter!} 
      localizationRow={localization?.localizationRow!}
      localizationColumn={localization?.localizationColumn!}/>}
    </div>
  )
}
