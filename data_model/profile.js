const user_profile = {
  userid: "1234567890",
  personal_info: {
    firstname: "John",
    lastname: "Doe",
    email: "john@email.com",
    mobile: "1234567890",
    address: "123, Main Street, City, State, Country - 12345",
  },
  career_info: {
    current_employer: "ABC Company",
    designation: "Software Engineer",
    industry: "IT",
  },
  links: {
    github: "somelinek.github.io",
    linkedin: "linkedin.com/in/somelinek",
    twitter: "twitter.com/somelinek",
    website: "somelinek.github.io",
  },
  default_files: {
    resume: "somelinek.github.io/resume.pdf",
    cover_letter: "somelinek.github.io/cover_letter.pdf",
    user_photo: "65830e9234e69002e02aeb6f",
  },
  job_application_profiles: [
    {
      id: "1234567890",
      company_name: "ABC Company",
      country: "German",
      position: "Software Engineer",
      status: "PROCESSING",
      applied_on: "2021-01-01",
      note: "some note",
    },
    {
      id: "1234567890",
      company_name: "XYZ Company",
      country: "France",
      position: "Software Engineer",
      status: "PROCESSING",
      applied_on: "2021-01-01",
      note: "some note",
    },
  ],
};

const cv = {
  id: "1234567890",
  cv_name: "some_name_cv",
  link: "somelinek.github.io/some_name_cv.pdf",
  uploaded_on: "2021-01-01",
};

const cover_letter = {
  id: "1234567890",
  cv_name: "some_name_cover_letter",
  link: "somelinek.github.io/some_name_cover_letter.pdf",
  uploaded_on: "2021-01-01",
};

const company_profile_status = {
  PROCESSING: "PROCESSING",
  ACCEPTED: "ACCEPTED",
  REJECTED: "REJECTED",
};

const company_profile = {
  company_name: "ABC Company",
  country: "German",
  industry: "Software Services",
  company_website: "somelinek.github.io",
  company_careers_page: "somelinek.github.io/careers",
  status: company_profile_status.PROCESSING,
  applied_on: "2021-01-01",
  progress: {
    overall_status: company_profile_status.PROCESSING,
    steps: [
      {
        title: "Applied",
        date: "2021-01-01",
        Description: "Applied for the job",
        uploads: [
          {
            id: "1234567890",
            name: "some_name_cv",
          },
          {
            id: "1234567890",
            name: "some_name_cover_letter",
          },
        ],
      },
      {
        title: "Interview",
        date: "2021-01-01",
        Description: "Interviewed for the job",
        uploads: [],
      },
      {
        title: "Offer",
        date: "2021-01-01",
        Description: "Got the offer",
        uploads: [],
      },
    ],
  },
};

const user_files = [
  {
    userid: "1234567890",
    user_cvs: [
      {
        cv_name: "some_name_cv",
        cv_id: "1234567890",
      },
      {
        cv_name: "some_name_cv",
        cv_id: "1234567890",
      },
    ],
    user_cover_letters: [
      {
        cover_letter_name: "some_name_cover_letter",
        cover_letter_id: "1234567890",
      },
      {
        cover_letter_name: "some_name_cover_letter",
        cover_letter_id: "1234567890",
      },
    ],
  },
];

const job_application_profiles = [
  {
    id: "1234567890",
    userid: "1234567890",
    company_name: "ABC Company",
    country: "German",
    industry: "Software Services",
    company_website: "somelinek.github.io",
    company_careers_page: "somelinek.github.io/careers",
    status: company_profile_status.PROCESSING,
    applied_on: "2021-01-01",
    progress: {
      overall_status: company_profile_status.PROCESSING,
      steps: [
        {
          title: "Applied",
          date: "2021-01-01",
          Description: "Applied for the job",
          uploads: [
            {
              id: "1234567890",
              name: "some_name_cv",
            },
            {
              id: "1234567890",
              name: "some_name_cover_letter",
            },
          ],
        },
        {
          title: "Interview",
          date: "2021-01-01",
          Description: "Interviewed for the job",
          uploads: [],
        },
        {
          title: "Offer",
          date: "2021-01-01",
          Description: "Got the offer",
          uploads: [],
        },
      ],
    },
  },
  {
    id: "1234567890",
    userid: "1234567890",
    company_name: "ABC Company",
    country: "German",
    industry: "Software Services",
    company_website: "somelinek.github.io",
    company_careers_page: "somelinek.github.io/careers",
    status: company_profile_status.PROCESSING,
    applied_on: "2021-01-01",
    progress: {
      overall_status: company_profile_status.PROCESSING,
      steps: [
        {
          title: "Applied",
          date: "2021-01-01",
          Description: "Applied for the job",
          uploads: [
            {
              id: "1234567890",
              name: "some_name_cv",
            },
            {
              id: "1234567890",
              name: "some_name_cover_letter",
            },
          ],
        },
        {
          title: "Interview",
          date: "2021-01-01",
          Description: "Interviewed for the job",
          uploads: [],
        },
        {
          title: "Offer",
          date: "2021-01-01",
          Description: "Got the offer",
          uploads: [],
        },
      ],
    },
  },
];

const sample = {
  _id: { $oid: "65830e9234e69002e02aeb6f" },
  personal_info: {
    firstname: "John",
    lastname: "Doe",
    email: "john@email.com",
    mobile: "1234567890",
    address: "123, Main Street, City, State, Country - 12345",
    country: "Sri Lanka",
  },
  career_info: {
    current_employer: "ABC Company",
    designation: "Software Engineer",
    industry: "IT",
  },
  links: {
    github: "somelinek.github.io",
    linkedin: "linkedin.com/in/somelinek",
    twitter: "twitter.com/somelinek",
    website: "somelinek.github.io",
  },
  default_files: {
    resume: "somelinek.github.io/resume.pdf",
    cover_letter: "somelinek.github.io/cover_letter.pdf",
  },
  job_application_profiles: [],
  user_cvs: [],
  user_letters: [],
};

const test = {
  _id: { $oid: "65831fdd34e69002e02aeb71" },
  user_id: { $oid: "65830e9234e69002e02aeb6f" },
  company_name: "ABC Company",
  country: "German",
  industry: "Software Services",
  company_website: "somelinek.github.io",
  company_careers_page: "somelinek.github.io/careers",
  status: company_profile_status.PROCESSING,
  applied_on: "2021-01-01",
  progress: {
    steps: [
      {
        title: "Applied",
        date: "2021-01-01",
        Description: "Applied for the job",
        uploads: [
          {
            id: "1234567890",
            name: "some_name_cv",
          },
          {
            id: "1234567890",
            name: "some_name_cover_letter",
          },
        ],
      },
      {
        title: "Interview",
        date: "2021-01-01",
        Description: "Interviewed for the job",
        uploads: [],
      },
      {
        title: "Offer",
        date: "2021-01-01",
        Description: "Got the offer",
        uploads: [],
      },
    ],
  },
};
