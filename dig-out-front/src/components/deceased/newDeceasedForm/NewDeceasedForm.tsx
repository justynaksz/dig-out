import React, { ChangeEvent, FormEvent, useState } from 'react'
import api from '../../../api/axiosConfig.js'
import { DeceasedType } from '../deceased/Deceased.tsx'
import { GraveType } from '../../graves/grave/Grave.tsx'
import { LocalizationType } from '../../localizations/localization/Localization.tsx'

export const NewDeceasedForm = () => {
    const [deceased, setDeceased] = useState<DeceasedType>({} as DeceasedType)
    const [grave, setGrave] = useState<GraveType>({} as GraveType)
    const [localization, setLocalization] = useState<LocalizationType>({} as LocalizationType); 


    const handleInput = (e: ChangeEvent<HTMLInputElement>) => {
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

        addDeceased({
            ...deceased,
            id: '0',
        })
    }

    const addDeceased = async (newDeceased: DeceasedType) => {
        try {
            const createdDeceased = newDeceased
            await api.post("/deceased", createdDeceased)
        } catch(error) {
            console.log(error)
        }
    }

    return (
        <>
           <h4 className='title'> CREATE NEW DECEASED </h4>
            <form className='body' onSubmit={handleSubmit}>
                <label htmlFor='firstName'>
                    <input
                    type='text'
                    id='firstName'
                    name='firstName'
                    value={deceased?.firstName || ''}
                    onChange={handleInput}
                    placeholder='First name'
                    />
                </label>
                <label htmlFor='lastName'>
                    <input
                    type='text'
                    id='lastName'
                    name='lastName'
                    value={deceased?.lastName || ''}
                    onChange={handleInput}
                    placeholder='Last name'
                    />
                </label>
                <label htmlFor='birthDate'>
                    <input
                    type='text'
                    id='birthDate'
                    name='birthDate'
                    value={deceased?.birthDate || ''}
                    onChange={handleInput}
                    placeholder='Birth date'
                    />
                </label>
                <label htmlFor='deathDate'>
                    <input
                    type='text'
                    id='deathDate'
                    name='deathDate'
                    value={deceased?.deathDate || ''}
                    onChange={handleInput}
                    placeholder='Death date'
                    />
                </label>
                <label htmlFor='infectiousDisease'>
                    <input
                    type='text'
                    id='infectiousDisease'
                    name='infectiousDisease'
                    value={deceased?.infectiousDisease as unknown as string}
                    onChange={handleInput}
                    placeholder='Is infectious disease'
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
        </>
    )
}