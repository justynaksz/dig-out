import React, { ChangeEvent, useEffect } from 'react'
import { FormEvent, useState } from 'react'
import api from '../../../api/axiosConfig.js'
import { DeceasedType } from '../deceased/Deceased.tsx'
import { GraveType } from '../../graves/grave/Grave.tsx'
import { LocalizationType } from '../../localizations/localization/Localization.tsx'
import { DeceasedList } from '../deceasedList/DeceasedList.tsx'

export const DeceasedSearch = () => {
    const [deceased, setDeceased] = useState<DeceasedType>({} as DeceasedType)
    const [grave, setGrave] = useState<GraveType>({} as GraveType)
    const [localization, setLocalization] = useState<LocalizationType>({} as LocalizationType); 
    const [results, setResults] = useState<DeceasedType[]>()

    const getDeceasedList = async (deaceased : DeceasedType) => {
        try {
            const response = await api.post("/deceased/search", deaceased)
            console.log(response.data)
            setResults(response.data)
        } catch (error) {
            console.log(error)
        }
    }

    const handleDeceasedInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setDeceased((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleGraveInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setGrave((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleLocalizationInput = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target
        setLocalization((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault()

        setGrave(prev => ({
            ...prev,
            localization: localization
        }))

        setDeceased(prev => ({
            ...prev,
            grave: grave
        }))

        getDeceasedList(deceased)
    }

    return (
        <section id='deceased-search' className='deceased-search' data-aos='fade-up'>
            <div className="section-title">
                <h2>Find your loved ones</h2>
            </div>
            <form className='search' onSubmit={handleSubmit}>
            <label htmlFor='firstName'>
                    <input
                    type='text'
                    id='firstName'
                    name='firstName'
                    value={deceased?.firstName || ''}
                    onChange={handleDeceasedInput}
                    placeholder='First name'
                    />
                </label>
                <label htmlFor='lastName'>
                    <input
                    type='text'
                    id='lastName'
                    name='lastName'
                    value={deceased?.lastName || ''}
                    onChange={handleDeceasedInput}
                    placeholder='Last name'
                    />
                </label>
                <label htmlFor='birthDate'>
                    <input
                    type='text'
                    id='birthDate'
                    name='birthDate'
                    value={deceased?.birthDate || ''}
                    onChange={handleDeceasedInput}
                    placeholder='Birth date'
                    />
                </label>
                <label htmlFor='deathDate'>
                    <input
                    type='text'
                    id='deathDate'
                    name='deathDate'
                    value={deceased?.deathDate || ''}
                    onChange={handleDeceasedInput}
                    placeholder='Death date'
                    />
                </label>
                <label htmlFor='type'>
                    <input
                    type='text'
                    id='type'
                    name='type'
                    value={deceased?.grave?.type || ''}
                    onChange={handleGraveInput}
                    placeholder='Type'
                    />
                </label>
                <label htmlFor='cemetery'>
                    <input
                        type='text'
                        id='cemetery'
                        name='cemetery'
                        value={deceased?.grave?.localization?.cemetery || ''}
                        onChange={handleLocalizationInput}
                        placeholder='Cemetery'
                    />
                </label>
                <label htmlFor='quarter'>
                    <input
                        type='text'
                        id='quarter'
                        name='quarter'
                        value={deceased?.grave?.localization?.quarter || ''}
                        onChange={handleLocalizationInput}
                        placeholder='Quarter'
                    />
                </label>
                 <label htmlFor='localizationRow'>
                <input
                    type='text'
                    id='localizationRow'
                    name='localizationRow'
                    value={deceased?.grave?.localization?.localizationRow || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Row'
                />
                </label>
                <label htmlFor='localizationColumn'>
                <input
                    type='text'
                    id='localizationColumn'
                    name='localizationColumn'
                    value={deceased?.grave?.localization?.localizationColumn || ''}
                    onChange={handleLocalizationInput}
                    placeholder='Column'
                />
                </label>
                <label htmlFor='owner'>
                <input
                    type='text'
                    id='graveOwner'
                    name='graveOwner'
                    value={deceased?.grave?.graveOwner || ''}
                    onChange={handleGraveInput}
                    placeholder='Owner'
                />
                </label>
            <button className='button' type='submit'>Add grave</button>
            </form>
            <DeceasedList deceasedList={results!}/>
        </section>
    )
}